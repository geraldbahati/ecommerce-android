package com.example.ecommerceapp.features.auth.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ecommerceapp.config.Constants

@Entity(tableName = Constants.USER_TABLE_NAME)
data class UserEntity(
    @PrimaryKey val id: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val accountStatus: String,
    val userRole: String,
    val twoFactorAuth: Boolean
)