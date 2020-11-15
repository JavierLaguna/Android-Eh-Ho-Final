package io.keepcoding.eh_ho.repositories.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import io.keepcoding.eh_ho.models.UserInfo
import java.util.*

class Converters {

    @TypeConverter
    fun userInfoToJson(value: UserInfo) = Gson().toJson(value)

    @TypeConverter
    fun jsonToUserInfo(value: String) = Gson().fromJson(value, UserInfo::class.java)

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.getTime()
    }

}