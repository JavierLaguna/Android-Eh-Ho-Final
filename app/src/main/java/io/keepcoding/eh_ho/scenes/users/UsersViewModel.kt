package io.keepcoding.eh_ho.scenes.users

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.models.Period
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.repositories.UsersRepository
import io.keepcoding.eh_ho.repositories.db.EhHoRoomDatabase
import io.keepcoding.eh_ho.repositories.models.UsersResponse
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.UsersServiceImpl
import retrofit2.Response
import java.util.*

class UsersViewModel(private val context: Application) : ViewModel() {

    private val usersRepository: UsersRepository = UsersServiceImpl()
    private val usersLocalRepository = EhHoRoomDatabase.getInstance(context).usersDao()
    private val users = mutableListOf<User>()
    private var isLoading = false
        set(value) {
            field = value
            delegate?.updateLoadingState(value)
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
        fetchUsers()
    }

    fun refreshUsers() {
        users.clear()
        fetchUsers()
    }

    private fun fetchUsers() {
        isLoading = true

        usersRepository.getUsers(period = Period.weekly, cb = object :
            DiscourseService.CallbackResponse<UsersResponse> {

            override fun onResponse(response: UsersResponse) {
                response.users?.let {
                    saveUsers(it)

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

    private fun saveUsers(users: List<User>) {
//        usersLocalRepository.insertAll(*users.toTypedArray())
        users.forEach {
            usersLocalRepository.insertUser(it)
        }

//        println()
//
//        class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
//            override fun doInBackground(vararg params: Void?): Void? {
//                handler()
//                return null
//            }
//        }
//
//        doAsync {
//            users.forEach {
//                usersLocalRepository.insertUser(it)
//            }
//        }.execute()
    }
}