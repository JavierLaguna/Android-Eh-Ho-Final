package io.keepcoding.eh_ho.scenes.userDetail

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Observer
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.models.UserDetail
import io.keepcoding.eh_ho.repositories.UsersRepository
import io.keepcoding.eh_ho.repositories.db.EhHoRoomDatabase
import io.keepcoding.eh_ho.repositories.models.UserDetailResponse
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.UsersServiceImpl
import io.keepcoding.eh_ho.utils.DoAsync
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class UserDetailViewModel(context: Application, private val owner: LifecycleOwner) : ViewModel() {

    private val usersRepository: UsersRepository = UsersServiceImpl()
    private val usersLocalRepository = EhHoRoomDatabase.getInstance(context).userDetailDao()
    private var user: User? = null

    var avatarUrl = ""
    var nickname = ""
    var name = ""
    var lastConnection = ""
    var topicsCreated = "0"
    var likesGiven = "0"
    var likesReceived = "0"
    var isMod = false

    var delegate: UserDetailViewModelDelegate? = null

    fun initialize(user: User) {
        this.user = user

        listenUserDetail()
        fetchUserDetail()

        this.avatarUrl = user.userInfo?.getAvatarURL(185) ?: ""
        this.nickname = user.userInfo?.username ?: ""
        this.name = user.userInfo?.name ?: ""
        this.topicsCreated = user.postCount.toString()
        this.likesGiven = user.likesGiven.toString()
        this.likesReceived = user.likesReceived.toString()

        delegate?.updateUserInfo()
    }

    private fun listenUserDetail() {
        user?.id?.let { id ->

            usersLocalRepository.getBy(id).observe(owner, Observer { userDetail ->
                userDetail?.let { userDetail ->
                    isMod = userDetail.moderator ?: false
                    userDetail.lastPostedAt?.let {
                        val formatter =
                            SimpleDateFormat("E, d MMM yyyy HH:mm", Locale.getDefault())
                        lastConnection = formatter.format(it)
                    }

                    delegate?.updateUserInfo()
                }
            })
        }
    }

    private fun fetchUserDetail() {
        user?.userInfo?.username?.let { username ->

            usersRepository.getUserDetail(username, cb = object :
                DiscourseService.CallbackResponse<UserDetailResponse> {

                override fun onResponse(response: UserDetailResponse) {
                    response.userDetail?.let { userDetail ->
                        saveUsers(userDetail)
                    }
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    delegate?.onErrorGettingUserDetail()
                }
            })
        }
    }

    private fun saveUsers(userDetail: UserDetail) {
        DoAsync {
            usersLocalRepository.insert(userDetail)
        }.execute()
    }
}