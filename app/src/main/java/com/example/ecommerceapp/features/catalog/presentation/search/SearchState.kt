package com.example.ecommerceapp.features.catalog.presentation.search

import com.example.ecommerceapp.features.catalog.domain.models.Category

data class SearchState(
    val isLoading: Boolean = false,

    val loadedCategories: List<Category> = emptyList(),
    val query: String = "",
    val isLoadingCategories: Boolean = false,
    val errorMessage: String? = null
)