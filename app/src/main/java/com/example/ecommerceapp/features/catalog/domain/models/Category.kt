package com.example.ecommerceapp.features.catalog.domain.models


data class Category (
    val id: String,
    val name: String,
    val description: String,
    val imageURL: String,
    val seoKeywords: String,
    val isActive: Boolean,
)