package io.keepcoding.eh_ho.repositories.services

import io.keepcoding.eh_ho.repositories.services.models.LatestTopicsResponse
import io.keepcoding.eh_ho.repositories.services.models.TopicDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Url


interface TopicsApi {

    @GET("/latest.json")
    @Headers("Content-Type: application/json")
    fun getTopics(): Call<LatestTopicsResponse>

    @GET
    @Headers("Content-Type: application/json")
    fun getTopicsPage(@Url topicsPageUrl: String): Call<LatestTopicsResponse>

    @GET("/t/{topicId}.json")
    @Headers("Content-Type: application/json")
    fun getTopicDetails(@Path("topicId") topicId: Int): Call<TopicDetailResponse>

}