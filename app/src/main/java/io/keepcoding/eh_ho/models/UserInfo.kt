package io.keepcoding.eh_ho.models

import com.google.gson.annotations.SerializedName
import io.keepcoding.eh_ho.BuildConfig
import java.io.Serializable

data class UserInfo(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("avatar_template")
    val avatarTemplate: String? = null,

    @field:SerializedName("username")
    val username: String? = null

) : Serializable {

    fun getAvatarURL(imageSize: Int = 100): String {
        val avatarUrl = avatarTemplate?.replace("{size}", imageSize.toString())
        return "${BuildConfig.DiscourseDomain}$avatarUrl"
    }
}