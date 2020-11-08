package io.keepcoding.eh_ho.scenes.topics

import android.app.Application
import androidx.lifecycle.ViewModel
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
//            delegate?.updateLoadingState(value)
        }

//    var delegate: AddCitiesViewModelDelegate? = null

    fun refreshTopics() {
        nextPageUrl = null
//        topicViewModels = [TopicPinnedCellViewModel()]

        fetchTopics()
    }

    private fun fetchTopics() {
        isLoading = true

        topicsRepository.getLatestTopics(object : DiscourseService.CallbackResponse<LatestTopicsResponse> {

                override fun onResponse(response: LatestTopicsResponse) {
//                    cities = response
                    isLoading = false
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
//                    delegate?.showError()
                    isLoading = false
                }
            })
    }
}