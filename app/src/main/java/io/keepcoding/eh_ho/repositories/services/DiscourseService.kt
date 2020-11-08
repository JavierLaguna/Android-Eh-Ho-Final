package io.keepcoding.eh_ho.repositories.services

import io.keepcoding.eh_ho.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class DiscourseService {

    interface CallbackResponse<T> {
        fun onResponse(response: T)
        fun onFailure(t: Throwable, res: Response<*>? = null)
    }

    val topicsApi: TopicsApi

    init {
        val timeout: Long = 6 * 1000

        val client = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.DiscourseDomain)
            .build()

        topicsApi = retrofit.create(TopicsApi::class.java)
    }
}