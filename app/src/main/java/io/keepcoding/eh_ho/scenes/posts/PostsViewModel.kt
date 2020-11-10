package io.keepcoding.eh_ho.scenes.posts

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.models.Post
import io.keepcoding.eh_ho.models.Topic
import io.keepcoding.eh_ho.repositories.TopicsRepository
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.TopicsServiceImpl
import io.keepcoding.eh_ho.repositories.services.models.TopicDetailResponse
import retrofit2.Response


class PostsViewModel(private val context: Application) : ViewModel() {

    private val topicsRepository: TopicsRepository = TopicsServiceImpl()
    private var topic: Topic? = null
    private var chunkSize: Int? = null
    private var allPostIds: List<Int> = listOf()

    var delegate: PostsViewModelDelegate? = null
    val posts = mutableListOf<Post>()

    fun initialize(topic: Topic) {
        this.topic = topic
        fetchTopicDetail()
    }

    private fun fetchTopicDetail() {
        topic?.id?.let { topicId ->

            topicsRepository.getTopicDetail(topicId, object :
                DiscourseService.CallbackResponse<TopicDetailResponse> {

                override fun onResponse(response: TopicDetailResponse) {
                    chunkSize = response.chunkSize
                    response.postStream?.let { postStream ->
                        postStream.posts?.let { it ->
                            posts.addAll(it)
                            delegate?.updatePosts()
                        }

                        postStream.allPostIds?.let { it ->
                            allPostIds = it
                        }
                    }

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