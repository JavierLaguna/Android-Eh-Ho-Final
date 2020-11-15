package io.keepcoding.eh_ho.repositories.db

import androidx.lifecycle.LiveData
import androidx.room.*
import io.keepcoding.eh_ho.models.User

@Dao
abstract class UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(users: List<User>)

    @Query("SELECT * FROM User")
    abstract fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE id = :userId")
    abstract fun getBy(userId: Int): LiveData<User?>

    @Delete
    abstract fun delete(user: User)
}