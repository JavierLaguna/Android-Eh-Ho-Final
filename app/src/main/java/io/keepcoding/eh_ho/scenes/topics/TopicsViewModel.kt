package io.keepcoding.eh_ho.scenes.topics

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.eh_ho.repositories.TopicsRepository
import io.keepcoding.eh_ho.repositories.services.TopicsServiceImpl

class TopicsViewModel(private val context: Application) : ViewModel() {

    private val topicsRepository: TopicsRepository = TopicsServiceImpl()
    
}