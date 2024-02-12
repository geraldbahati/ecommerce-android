package com.example.ecommerceapp.features.auth.data.remote.dto

import com.squareup.moshi.Json

data class LoginResponse(
    @field:Json(name = "access_token") val accessToken: String,
    @field:Json(name = "refresh_token") val tokenType: String,
)