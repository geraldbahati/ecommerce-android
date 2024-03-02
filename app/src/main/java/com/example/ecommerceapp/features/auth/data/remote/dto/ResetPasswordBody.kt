package com.example.ecommerceapp.features.auth.data.remote.dto

import com.squareup.moshi.Json

data class ResetPasswordBody(
    @field:Json(name = "email") val email: String
)