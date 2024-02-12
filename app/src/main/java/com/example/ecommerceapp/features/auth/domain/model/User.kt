package com.example.ecommerceapp.features.auth.domain.model

import java.util.Date

data class User (
    val id: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val accountStatus: String,
    val userRole: String,
    val twoFactorAuth: Boolean
)


