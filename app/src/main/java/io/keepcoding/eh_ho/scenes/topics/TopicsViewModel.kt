package io.keepcoding.eh_ho.scenes.topics

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.models.Topic
import io.keepcoding.eh_ho.repositories.TopicsRepository
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.TopicsServiceImpl
import io.keepcoding.eh_ho.repositories.services.models.LatestTopicsResponse
import retrofit2.Response

class TopicsViewModel(private val context: Application) : ViewModel() {

    private val topicsRepository: TopicsRepository = TopicsServiceImpl()

    private var nextPageUrl: String? = null
    private var isLoading = false
        set(value) {
            field = value
            delegate?.updateLoadingState(value && topics.isEmpty())
        }
    private val topics = mutableListOf<Topic>()

    var delegate: TopicsViewModelDelegate? = null

    fun refreshTopics() {
        nextPageUrl = null
        topics.clear()
        delegate?.updateTopics(topics)

        fetchTopics()
    }

    fun fetchMoreTopics() {
        if (nextPageUrl != null) {
            fetchTopics()
            nextPageUrl = null
        }
    }

    private fun fetchTopics() {
        if (isLoading) {
            return
        }

        isLoading = true

        topicsRepository.getLatestTopics(nextPageUrl, object :
            DiscourseService.CallbackResponse<LatestTopicsResponse> {

            override fun onResponse(response: LatestTopicsResponse) {
                response.topicList?.let {
                    nextPageUrl = it.moreTopicsUrl

                    it.topics?.let { newTopics ->
                        topics.addAll(newTopics)
                        delegate?.updateTopics(topics)
                    }
                }

                isLoading = false
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {
                isLoading = false
                delegate?.onErrorGettingTopics()
            }
        })
    }
}