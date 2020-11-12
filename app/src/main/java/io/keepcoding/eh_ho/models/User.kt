package io.keepcoding.eh_ho.models

import com.google.gson.annotations.SerializedName


data class User(

    @field:SerializedName("days_visited")
    val daysVisited: Int? = null,

    @field:SerializedName("posts_read")
    val postsRead: Int? = null,

    @field:SerializedName("topics_entered")
    val topicsEntered: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("likes_given")
    val likesGiven: Int? = null,

    @field:SerializedName("post_count")
    val postCount: Int? = null,

    @field:SerializedName("likes_received")
    val likesReceived: Int? = null,

    @field:SerializedName("user")
    val user: UserInfo? = null,

    @field:SerializedName("topic_count")
    val topicCount: Int? = null
) {

    data class UserInfo(

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("title")
        val title: Any? = null,

        @field:SerializedName("avatar_template")
        val avatarTemplate: String? = null,

        @field:SerializedName("username")
        val username: String? = null
    )
}