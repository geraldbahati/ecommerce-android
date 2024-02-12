package com.example.ecommerceapp.features.auth.data.Mapper

import com.example.ecommerceapp.features.auth.data.entity.UserEntity
import com.example.ecommerceapp.features.auth.domain.model.User

fun UserEntity.toUser(): User {
    return User(
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