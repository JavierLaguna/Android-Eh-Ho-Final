package io.keepcoding.eh_ho.repositories.services.models

import com.google.gson.annotations.SerializedName
import io.keepcoding.eh_ho.models.Post
import io.keepcoding.eh_ho.models.TopicDetails

data class TopicDetailResponse(

	@field:SerializedName("chunk_size")
	val chunkSize: Int? = null,

	@field:SerializedName("details")
	val details: TopicDetails? = null,

	@field:SerializedName("post_stream")
	val postStream: PostStream? = null
)

data class PostStream(

	@field:SerializedName("stream")
	val allPostIds: List<Int>? = null,

	@field:SerializedName("posts")
	val posts: List<Post>? = null
)


