package io.keepcoding.eh_ho.scenes.userDetail


interface UserDetailViewModelDelegate {

    fun updateUserInfo()
    fun onErrorGettingUserDetail()
}