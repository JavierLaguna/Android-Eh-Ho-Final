package io.keepcoding.eh_ho.scenes.topics

import io.keepcoding.eh_ho.models.Topic

interface TopicsViewModelDelegate {

    fun updateTopics()
    fun updateLoadingState(show: Boolean)
    fun onErrorGettingTopics()
}