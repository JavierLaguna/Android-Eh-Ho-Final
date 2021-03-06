package io.keepcoding.eh_ho.repositories.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.keepcoding.eh_ho.models.User
import io.keepcoding.eh_ho.models.UserDetail


@Database(entities = [User::class, UserDetail::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EhHoRoomDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
    abstract fun userDetailDao(): UserDetailDao

    companion object {
        private var instance: EhHoRoomDatabase? = null

        fun getInstance(context: Context): EhHoRoomDatabase {
            if (instance == null) {

                synchronized(EhHoRoomDatabase::class) {
                    instance = Room
                        .databaseBuilder(
                            context.applicationContext,
                            EhHoRoomDatabase::class.java,
                            "eh_ho_db"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance!!
        }
    }
}