package io.keepcoding.eh_ho.repositories.services

import io.keepcoding.eh_ho.repositories.services.models.LatestTopicsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


interface TopicsApi {

    @GET("/latest.json")
    @Headers("Content-Type: application/json")
    fun getTopics(): Call<LatestTopicsResponse>

}