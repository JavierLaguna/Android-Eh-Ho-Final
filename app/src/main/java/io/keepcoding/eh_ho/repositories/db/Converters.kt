package io.keepcoding.eh_ho.repositories.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import io.keepcoding.eh_ho.models.User

class Converters {

    @TypeConverter
    fun userToJson(value: User) = Gson().toJson(value)

    @TypeConverter
    fun userInfoToJson(value: User.UserInfo) = Gson().toJson(value)

    @TypeConverter
    fun userListToJson(value: List<User>) = Gson().toJson(value)

    @TypeConverter
    fun userInfoListToJson(value: List<User.UserInfo>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToUser(value: String) = Gson().fromJson(value, User::class.java)

    @TypeConverter
    fun jsonToUserInfo(value: String) = Gson().fromJson(value, User.UserInfo::class.java)

}