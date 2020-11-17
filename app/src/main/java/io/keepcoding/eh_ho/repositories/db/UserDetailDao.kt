package io.keepcoding.eh_ho.repositories.db

import androidx.lifecycle.LiveData
import androidx.room.*
import io.keepcoding.eh_ho.models.UserDetail


@Dao
abstract class UserDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(userDetail: UserDetail)

    @Query("SELECT * FROM UserDetail")
    abstract fun getAll(): LiveData<List<UserDetail>>

    @Query("SELECT * FROM UserDetail WHERE id = :userId")
    abstract fun getBy(userId: Int): LiveData<UserDetail?>

    @Delete
    abstract fun delete(userDetail: UserDetail)
}