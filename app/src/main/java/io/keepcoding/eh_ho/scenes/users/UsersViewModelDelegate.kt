package io.keepcoding.eh_ho.scenes.users

interface UsersViewModelDelegate {

    fun updateUsers()
    fun updateLoadingState(show: Boolean)
    fun onErrorGettingUsers()
}