package io.keepcoding.eh_ho.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Topic(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("views")
    val views: Int? = null,

    @field:SerializedName("last_posted_at")
    val lastPostedAt: String? = null,

    @field:SerializedName("like_count")
    val likeCount: Int? = null,

    @field:SerializedName("last_poster_username")
    val lastPosterUsername: String? = null,

    @field:SerializedName("posters")
    val posters: List<Poster?>? = null,

    @field:SerializedName("reply_count")
    val replyCount: Int? = null,

    @field:SerializedName("posts_count")
    val postsCount: Int? = null

) : Serializable {

    // Nested class
    data class Poster(

        @field:SerializedName("user_id")
        val userId: Int? = null,

        @field:SerializedName("primary_group_id")
        val primaryGroupId: Int? = null,

        @field:SerializedName("extras")
        val extras: String? = null,

        @field:SerializedName("description")
        val description: String? = null

    ) : Serializable

}






