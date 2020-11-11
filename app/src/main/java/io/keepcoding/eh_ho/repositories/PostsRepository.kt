package io.keepcoding.eh_ho.repositories

import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.services.models.SpecificPostsResponse

interface PostsRepository {

    fun getPostsOfTopic(topicId: Int, postIds: List<Int>, cb: DiscourseService.CallbackResponse<SpecificPostsResponse>)
}