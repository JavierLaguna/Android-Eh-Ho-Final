package io.keepcoding.eh_ho.utils

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.eh_ho.scenes.posts.PostsViewModel
import io.keepcoding.eh_ho.scenes.topics.TopicsViewModel
import io.keepcoding.eh_ho.scenes.userDetail.UserDetailViewModel
import io.keepcoding.eh_ho.scenes.users.UsersViewModel
import java.lang.IllegalArgumentException

class CustomViewModelFactory(
    private val application: Application,
    private val owner: LifecycleOwner
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(TopicsViewModel::class.java) -> TopicsViewModel(application)
                isAssignableFrom(PostsViewModel::class.java) -> PostsViewModel(application)
                isAssignableFrom(UsersViewModel::class.java) -> UsersViewModel(application, owner)
                isAssignableFrom(UserDetailViewModel::class.java) -> UserDetailViewModel(application, owner)
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}