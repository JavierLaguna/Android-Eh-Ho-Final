package io.keepcoding.eh_ho.repositories.services.models

import com.google.gson.annotations.SerializedName
import io.keepcoding.eh_ho.models.Topic
import java.io.Serializable


data class LatestTopicsResponse(

	@field:SerializedName("topic_list")
	val topicList: TopicList? = null

) : Serializable

data class TopicList(

	@field:SerializedName("more_topics_url")
	val moreTopicsUrl: String? = null,

	@field:SerializedName("topics")
	val topics: List<Topic?>? = null

) : Serializable
