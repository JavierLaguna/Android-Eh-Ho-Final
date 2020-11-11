package io.keepcoding.eh_ho.repositories.services.models

import com.google.gson.annotations.SerializedName
import io.keepcoding.eh_ho.models.PostStream
import io.keepcoding.eh_ho.models.TopicDetails


data class SpecificPostsResponse(

    @field:SerializedName("post_stream")
    val postStream: PostStream? = null
)



