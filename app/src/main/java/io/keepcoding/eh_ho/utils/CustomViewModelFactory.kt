package io.keepcoding.eh_ho.utils

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CustomViewModelFactory(
    private val application: Application,
    private val owner: LifecycleOwner
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
//                isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel(application, owner)
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}