package com.example.ecommerceapp.features.auth.data.remote.dto

import com.squareup.moshi.Json

data class RegisterRequestBody(
    @field:Json(name = "email") val email: String,
    @field:Json(name = "password") val password: String,
    @field:Json(name = "first_name") val firstName: String,
    @field:Json(name = "last_name") val lastName: String,
    @field:Json(name = "user_role") val userRole: String
)