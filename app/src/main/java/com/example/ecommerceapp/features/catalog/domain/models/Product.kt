package com.example.ecommerceapp.features.catalog.domain.models

import androidx.compose.runtime.Immutable

@Immutable
data class Product (
    val id: String,
    val name: String,
    val description: String,
    val imageURL: String,
    val price: Double,
    val stock: Int,
    val subCategoryId: String,
    val rating: Double,
    val reviewCount: Int,
    val discountRate: String,
    val keywords: String
)