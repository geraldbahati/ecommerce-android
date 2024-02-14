package com.example.ecommerceapp.features.auth.data.local

import androidx.compose.ui.unit.Constraints
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.auth.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM ${Constants.USER_TABLE_NAME} LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Query("DELETE FROM ${Constants.USER_TABLE_NAME}")
    suspend fun clearUser()

}