package io.keepcoding.eh_ho.repositories.services

import io.keepcoding.eh_ho.models.Period
import io.keepcoding.eh_ho.repositories.UsersRepository
import io.keepcoding.eh_ho.repositories.models.UserDetailResponse
import io.keepcoding.eh_ho.repositories.models.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersServiceImpl : UsersRepository {

    override fun getUsers(period: Period, cb: DiscourseService.CallbackResponse<UsersResponse>) {

        DiscourseService().usersApi.getUsers(period).enqueue(object : Callback<UsersResponse> {

            override fun onResponse(
                call: Call<UsersResponse>,
                response: Response<UsersResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    cb.onResponse(response.body()!!)
                } else {
                    cb.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                cb.onFailure(t)
            }
        })
    }

    override fun getUserDetail(
        username: String,
        cb: DiscourseService.CallbackResponse<UserDetailResponse>
    ) {

        DiscourseService().usersApi.getUserDetail(username)
            .enqueue(object : Callback<UserDetailResponse> {

                override fun onResponse(
                    call: Call<UserDetailResponse>,
                    response: Response<UserDetailResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        cb.onResponse(response.body()!!)
                    } else {
                        cb.onFailure(Throwable(response.message()), response)
                    }
                }

                override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                    cb.onFailure(t)
                }
            })
    }
}