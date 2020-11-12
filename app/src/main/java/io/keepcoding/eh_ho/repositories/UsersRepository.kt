package io.keepcoding.eh_ho.repositories

import io.keepcoding.eh_ho.models.Period
import io.keepcoding.eh_ho.repositories.services.DiscourseService
import io.keepcoding.eh_ho.repositories.models.UsersResponse

interface UsersRepository {

    fun getUsers(period: Period, cb: DiscourseService.CallbackResponse<UsersResponse>)
}