package io.keepcoding.eh_ho.scenes.posts

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.models.Post
import io.keepcoding.eh_ho.models.Topic
import io.keepcoding.eh_ho.repositories.PostsRepository
import io.keepcoding.eh_ho.repositories.TopicsRepository
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.PostsServiceImpl
import io.keepcoding.eh_ho.repositories.services.TopicsServiceImpl
import io.keepcoding.eh_ho.repositories.models.SpecificPostsResponse
import io.keepcoding.eh_ho.repositories.models.TopicDetailResponse
import retrofit2.Response


class PostsViewModel(private val context: Application) : ViewModel() {

    private val topicsRepository: TopicsRepository = TopicsServiceImpl()
    private val postsRepository: PostsRepository = PostsServiceImpl()
    private var topic: Topic? = null
    private var chunkSize: Int? = null
    private var allPostIds: List<Int> = listOf()
    private var isGettingMorePosts = false
    private var isLoading = false
        set(value) {
            field = value
            delegate?.updateLoadingState(value && posts.isEmpty())
        }

    var delegate: PostsViewModelDelegate? = null
    val posts = mutableListOf<Post>()

    fun initialize(topic: Topic) {
        this.topic = topic
    }

    fun fetchTopicDetail() {
        topic?.id?.let { topicId ->

            isLoading = true

            topicsRepository.getTopicDetail(topicId, object :
                DiscourseService.CallbackResponse<TopicDetailResponse> {

                override fun onResponse(response: TopicDetailResponse) {
                    chunkSize = response.chunkSize
                    response.postStream?.let { postStream ->
                        postStream.posts?.let { it ->
                            posts.clear()
                            posts.addAll(it)
                            delegate?.updatePosts()
                        }

                        postStream.allPostIds?.let { it ->
                            allPostIds = it
                        }
                    }

                    isLoading = false
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    isLoading = false
                    delegate?.onErrorGettingTopicDetail()
                }
            })
        }
    }

    fun fetchMorePosts() {
        val currentPostCount = posts.size
        val chunkSize = this.chunkSize ?: 0
        if (isGettingMorePosts || chunkSize == 0 || currentPostCount >= allPostIds.size) {
            return
        }

        topic?.id?.let { topicId ->
            isGettingMorePosts = true

            val to =
                if (currentPostCount + chunkSize < allPostIds.size) currentPostCount + chunkSize else allPostIds.size
            val nextPostIds = allPostIds.subList(currentPostCount, to)

            postsRepository.getPostsOfTopic(topicId, nextPostIds, object :
                DiscourseService.CallbackResponse<SpecificPostsResponse> {

                override fun onResponse(response: SpecificPostsResponse) {
                    response.postStream?.posts?.let { newPosts ->
                        posts.addAll(newPosts)
                        delegate?.updatePosts()
                    }
                    isGettingMorePosts = false
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    delegate?.onErrorGettingPosts()
                    isGettingMorePosts = false
                }
            })
        }
    }
}
