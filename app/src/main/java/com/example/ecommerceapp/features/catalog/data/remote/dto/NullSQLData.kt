package com.example.ecommerceapp.features.catalog.data.remote.dto

import com.squareup.moshi.Json

data class NullSQLData (
    @field: Json(name = "String") val string: String,
    @field: Json(name = "Valid") val valid: Boolean
)