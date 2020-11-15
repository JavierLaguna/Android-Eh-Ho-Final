package io.keepcoding.eh_ho.repositories.db

import androidx.lifecycle.LiveData
import androidx.room.*
import io.keepcoding.eh_ho.models.User

@Dao
abstract class UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(user: User)

    @Query("SELECT * FROM users_table")
    abstract fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM users_table WHERE id = :userId")
    abstract fun getUser(userId: Int): LiveData<User?>

    @Delete
    abstract fun deleteUser(user: User)
}