package io.keepcoding.eh_ho.repositories.services

import io.keepcoding.eh_ho.repositories.services.models.SpecificPostsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PostsApi {

    @GET("/t/{topicId}/posts.json")
    @Headers("Content-Type: application/json")
    fun getPostsOfTopic(
        @Path("topicId") topicId: Int,
        @Query("post_ids[]") postIds: List<Int>
    ): Call<SpecificPostsResponse>
}
