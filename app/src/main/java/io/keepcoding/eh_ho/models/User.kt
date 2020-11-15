package io.keepcoding.eh_ho.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "users_table")
data class User(

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("days_visited")
    val daysVisited: Int? = null,

    @field:SerializedName("posts_read")
    val postsRead: Int? = null,

    @field:SerializedName("topics_entered")
    val topicsEntered: Int? = null,

    @field:SerializedName("likes_given")
    val likesGiven: Int? = null,

    @field:SerializedName("post_count")
    val postCount: Int? = null,

    @field:SerializedName("likes_received")
    val likesReceived: Int? = null,

    @field:SerializedName("topic_count")
    val topicCount: Int? = null,

    @field:SerializedName("user")
    val userInfo: UserInfo? = null

) : Serializable