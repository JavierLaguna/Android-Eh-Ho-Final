package io.keepcoding.eh_ho.repositories.models

import com.google.gson.annotations.SerializedName
import io.keepcoding.eh_ho.models.PostStream


data class SpecificPostsResponse(

    @field:SerializedName("post_stream")
    val postStream: PostStream? = null
)



