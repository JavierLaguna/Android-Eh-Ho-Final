package io.keepcoding.eh_ho.scenes.users

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.models.Period
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.repositories.UsersRepository
import io.keepcoding.eh_ho.repositories.db.EhHoRoomDatabase
import io.keepcoding.eh_ho.repositories.models.UsersResponse
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.UsersServiceImpl
import io.keepcoding.eh_ho.utils.DoAsync
import retrofit2.Response
import java.util.*

class UsersViewModel(context: Application, private val owner: LifecycleOwner) : ViewModel() {

    private val usersRepository: UsersRepository = UsersServiceImpl()
    private val usersLocalRepository = EhHoRoomDatabase.getInstance(context).usersDao()
    private val users = mutableListOf<User>()
    private var isLoading = false
        set(value) {
            field = value
            delegate?.updateLoadingState(value && users.isEmpty())
        }
    var searchText: String? = ""
        set(value) {
            field = value
            delegate?.updateUsers()
        }
    val filteredUsers: List<User>
        get() {
            searchText?.let { searchText ->
                if (searchText.isNotEmpty()) {
                    val fixedSearchText = searchText.toLowerCase(Locale.getDefault())

                    return users.filter {
                        val fixedTitle = it.userInfo?.name?.toLowerCase(Locale.getDefault())
                        fixedTitle?.contains(fixedSearchText) ?: false
                    }
                }
            }

            return users
        }

    var delegate: UsersViewModelDelegate? = null

    fun initialize() {
        listenUsers()
        fetchUsers()
    }

    fun refreshUsers() {
        users.clear()
        fetchUsers()
    }

    private fun listenUsers() {
        usersLocalRepository.getAll().observe(owner, Observer {
            if (it.isNotEmpty()) {
                users.clear()
                users.addAll(it)
                delegate?.updateUsers()
            }
        })
    }

    private fun fetchUsers() {
        isLoading = true

        usersRepository.getUsers(period = Period.weekly, cb = object :
            DiscourseService.CallbackResponse<UsersResponse> {

            override fun onResponse(response: UsersResponse) {
                response.users?.let {
                    saveUsers(it)
                }

                isLoading = false
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {
                isLoading = false
                delegate?.onErrorGettingUsers()
            }
        })
    }

    private fun saveUsers(users: List<User>) {
        DoAsync {
            usersLocalRepository.insert(users)
        }.execute()
    }
}

