package com.example.ecommerceapp.features.auth.data.remote.dto

import com.squareup.moshi.Json

data class MessageResponseDTO(
    @field:Json(name = "message") val message: String
)