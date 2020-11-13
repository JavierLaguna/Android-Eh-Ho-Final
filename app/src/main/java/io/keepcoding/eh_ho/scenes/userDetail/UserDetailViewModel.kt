package io.keepcoding.eh_ho.scenes.userDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.repositories.UsersRepository
import io.keepcoding.eh_ho.repositories.models.UserDetailResponse
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.UsersServiceImpl
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class UserDetailViewModel(private val context: Application) : ViewModel() {

    private val usersRepository: UsersRepository = UsersServiceImpl()
    private var user: User? = null

    var avatarUrl = ""
    var nickname = ""
    var name = ""
    var lastConnection = ""
    var likesReceived = "0"
    var isMod = false

    var delegate: UserDetailViewModelDelegate? = null

    fun initialize(user: User) {
        this.user = user

        fetchUserDetail()

        this.avatarUrl = user.userInfo?.getAvatarURL(185) ?: ""
        this.nickname = user.userInfo?.username ?: ""
        this.name = user.userInfo?.name ?: ""

        delegate?.updateUserInfo()
    }

    private fun fetchUserDetail() {
        user?.userInfo?.username?.let { username ->

            usersRepository.getUserDetail(username, cb = object :
                DiscourseService.CallbackResponse<UserDetailResponse> {

                override fun onResponse(response: UserDetailResponse) {
                    response.userDetail?.let { userDetail ->
                        likesReceived = userDetail.profileViewCount.toString()
                        isMod = userDetail.moderator ?: false
                        userDetail.lastPostedAt?.let {
                            val formatter =
                                SimpleDateFormat("E, d MMM yyyy HH:mm", Locale.getDefault())
                            lastConnection = formatter.format(it)
                        }

                        delegate?.updateUserInfo()
                    }
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    delegate?.onErrorGettingUserDetail()
                }
            })
        }
    }
}