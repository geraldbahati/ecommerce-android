package com.example.ecommerceapp.features.catalog.data.remote.dto

data class CreateCategoryParams(
    val name: String,
    val description: String,
    val imageURL: String,
    val seoKeywords: String
)