package io.keepcoding.eh_ho.scenes.posts

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.repositories.TopicsRepository
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.TopicsServiceImpl
import io.keepcoding.eh_ho.repositories.services.models.TopicDetailResponse
import retrofit2.Response


class PostsViewModel(private val context: Application) : ViewModel() {

    private val topicsRepository: TopicsRepository = TopicsServiceImpl()
    private var topicId: Int? = null

    fun initialize(topicId: Int) {
        this.topicId = topicId
        fetchTopicDetail()
    }

    private fun fetchTopicDetail() {
        topicId?.let { topicId ->

            topicsRepository.getTopicDetail(topicId, object :
                DiscourseService.CallbackResponse<TopicDetailResponse> {

                override fun onResponse(response: TopicDetailResponse) {

                    println()
//                    isLoading = false
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    println()
//                    isLoading = false
//                    delegate?.onErrorGettingTopics()
                }
            })
        }
    }
}