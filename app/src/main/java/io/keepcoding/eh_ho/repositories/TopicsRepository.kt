package io.keepcoding.eh_ho.repositories

import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.models.LatestTopicsResponse

interface TopicsRepository {

    fun getLatestTopics(cb: DiscourseService.CallbackResponse<LatestTopicsResponse>)
}