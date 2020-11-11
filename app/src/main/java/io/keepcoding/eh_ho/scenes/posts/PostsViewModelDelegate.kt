package io.keepcoding.eh_ho.scenes.posts

interface PostsViewModelDelegate {

    fun updatePosts()
    fun updateLoadingState(show: Boolean)
    fun onErrorGettingTopicDetail()
    fun onErrorGettingPosts()
}