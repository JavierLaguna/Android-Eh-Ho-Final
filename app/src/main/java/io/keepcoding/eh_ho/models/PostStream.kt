package io.keepcoding.eh_ho.models

import com.google.gson.annotations.SerializedName


data class PostStream(

    @field:SerializedName("stream")
    val allPostIds: List<Int>? = null,

    @field:SerializedName("posts")
    val posts: List<Post>? = null
)
