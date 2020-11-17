package io.keepcoding.eh_ho.repositories.models

import com.google.gson.annotations.SerializedName
import io.keepcoding.eh_ho.models.UserDetail

data class UserDetailResponse(

    @field:SerializedName("user")
    val userDetail: UserDetail? = null

)

