package com.example.ecommerceapp.features.auth.data.remote.dto

import com.squareup.moshi.Json

data class LoginBodyRequest (
    @field:Json(name = "email") val email: String,
    @field:Json(name = "password") val password: String
)