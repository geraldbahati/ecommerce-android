package com.example.ecommerceapp.features.catalog.presentation.categories

import com.example.ecommerceapp.features.catalog.domain.models.Category

data class CategoriesState(
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val selectedCategory: Category? = null
)