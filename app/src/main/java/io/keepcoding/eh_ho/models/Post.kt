package io.keepcoding.eh_ho.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: Date? = null,

    @field:SerializedName("updated_at")
    val updatedAt: Date? = null,

    @field:SerializedName("topic_id")
    val topicId: Int? = null,

    @field:SerializedName("cooked")
    val content: String? = null,

    @field:SerializedName("reads")
    val reads: Int? = null,

    @field:SerializedName("reply_count")
    val replyCount: Int? = null,

    @field:SerializedName("username")
    val author: String? = null
)