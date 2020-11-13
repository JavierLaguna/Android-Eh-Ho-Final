package io.keepcoding.eh_ho.scenes.userDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.models.User


class UserDetailViewModel(private val context: Application) : ViewModel() {

    var avatarUrl = ""
    var nickname = ""
    var name = ""
    var lastConnection = ""
    var likesReceived = "0"
    var isMod = false

    var delegate: UserDetailViewModelDelegate? = null

    private var user: User? = null

    fun initialize(user: User) {
        this.user = user

        this.avatarUrl = user.userInfo?.getAvatarURL(185) ?: ""
        this.nickname = user.userInfo?.username ?: ""
        this.name = user.userInfo?.name ?: ""
//        this.lastConnection = user.

        delegate?.updateUserInfo()

    }
}