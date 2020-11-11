package io.keepcoding.eh_ho.repositories.services

import io.keepcoding.eh_ho.repositories.PostsRepository
import io.keepcoding.eh_ho.repositories.services.models.SpecificPostsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsServiceImpl : PostsRepository {

    override fun getPostsOfTopic(
        topicId: Int,
        postIds: List<Int>,
        cb: DiscourseService.CallbackResponse<SpecificPostsResponse>
    ) {

        DiscourseService().postsApi.getPostsOfTopic(topicId, postIds)
            .enqueue(object : Callback<SpecificPostsResponse> {

                override fun onResponse(
                    call: Call<SpecificPostsResponse>,
                    response: Response<SpecificPostsResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        cb.onResponse(response.body()!!)
                    } else {
                        cb.onFailure(Throwable(response.message()), response)
                    }
                }

                override fun onFailure(call: Call<SpecificPostsResponse>, t: Throwable) {
                    cb.onFailure(t)
                }
            })
    }
}