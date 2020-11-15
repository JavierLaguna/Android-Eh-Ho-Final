package io.keepcoding.eh_ho.repositories.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import io.keepcoding.eh_ho.models.UserInfo

class Converters {

    @TypeConverter
    fun userInfoToJson(value: UserInfo) = Gson().toJson(value)


    @TypeConverter
    fun jsonToUserInfo(value: String) = Gson().fromJson(value, UserInfo::class.java)

}