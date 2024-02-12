package com.example.ecommerceapp.features.auth.data.remote.dto

import com.example.ecommerceapp.features.auth.data.entity.UserEntity
import com.squareup.moshi.Json

data class UserResponseDTO(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "first_name") val firstName: String,
    @field:Json(name = "last_name") val lastName: String,
    @field:Json(name = "user_role") val userRole: String,
    @field:Json(name = "account_status") val accountStatus: String,
    @field:Json(name = "two_factor_auth") val twoFactorAuth: Boolean
){
    fun toUser() = UserEntity(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        userRole = userRole,
        accountStatus = accountStatus,
        username = username,
        twoFactorAuth = twoFactorAuth
    )
}