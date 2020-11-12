package io.keepcoding.eh_ho.repositories.models

import com.google.gson.annotations.SerializedName
import io.keepcoding.eh_ho.models.User

data class UsersResponse(

    @field:SerializedName("directory_items")
    val users: List<User>? = null

)


