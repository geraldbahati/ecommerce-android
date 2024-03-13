package com.example.ecommerceapp.features.catalog.domain.models

import javax.annotation.concurrent.Immutable

@Immutable
data class SubCategory (
    val id: String,
    val categoryID: String,
    val name: String,
    val description: String?,
    val imageURL: String?,
    val seoKeywords: String?,
    val isActive: Boolean,
)