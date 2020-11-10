package io.keepcoding.eh_ho.repositories

import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.models.LatestTopicsResponse
import io.keepcoding.eh_ho.repositories.services.models.TopicDetailResponse

interface TopicsRepository {

    fun getLatestTopics(moreTopicsUrl: String?, cb: DiscourseService.CallbackResponse<LatestTopicsResponse>)
    fun getTopicDetail(topicId: Int, cb: DiscourseService.CallbackResponse<TopicDetailResponse>)
}