package io.keepcoding.eh_ho.repositories.services

import io.keepcoding.eh_ho.repositories.TopicsRepository
import io.keepcoding.eh_ho.repositories.services.models.LatestTopicsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TopicsServiceImpl : TopicsRepository {

    override fun getLatestTopics(cb: DiscourseService.CallbackResponse<LatestTopicsResponse>) {

        DiscourseService().topicsApi.getTopics().enqueue(object : Callback<LatestTopicsResponse> {

            override fun onResponse(call: Call<LatestTopicsResponse>, response: Response<LatestTopicsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<LatestTopicsResponse>, t: Throwable) {
                cb.onFailure(t)
            }

        })
    }

}