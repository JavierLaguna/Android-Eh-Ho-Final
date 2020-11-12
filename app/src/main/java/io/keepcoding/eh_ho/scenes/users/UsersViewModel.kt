package io.keepcoding.eh_ho.scenes.users

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.models.Period
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.repositories.UsersRepository
import io.keepcoding.eh_ho.repositories.models.UsersResponse
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.UsersServiceImpl
import retrofit2.Response

class UsersViewModel(private val context: Application) : ViewModel() {

    private val usersRepository: UsersRepository = UsersServiceImpl()
    private var isLoading = false
        set(value) {
            field = value
            delegate?.updateLoadingState(value)
        }

    val users = mutableListOf<User>()
    var delegate: UsersViewModelDelegate? = null

    fun initialize() {
        fetchUsers()
    }

    private fun fetchUsers() {
        isLoading = true

        usersRepository.getUsers(period = Period.weekly, cb = object :
            DiscourseService.CallbackResponse<UsersResponse> {

            override fun onResponse(response: UsersResponse) {
                response.users?.let {
                    users.addAll(it)
                    delegate?.updateUsers()
                }

                isLoading = false
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {
                isLoading = false
                delegate?.onErrorGettingUsers()
            }
        })
    }
}