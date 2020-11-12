package io.keepcoding.eh_ho.repositories.services

import io.keepcoding.eh_ho.models.Period
import io.keepcoding.eh_ho.repositories.models.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UsersApi {

    @GET("/directory_items.json")
    @Headers("Content-Type: application/json")
    fun getUsers(
        @Query("period") period: Period
    ): Call<UsersResponse>
}