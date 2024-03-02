package com.example.ecommerceapp.features.catalog.presentation.home

import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

data class HomeState(
    val isLoading: Boolean = false,
    var navigator: DestinationsNavigator? = null,

    val loadedCategories: List<Category> = emptyList(),
    val isLoadingCategories: Boolean = false,
    val categoriesErrorMessage: String? = null,

    val selectedCategory: Category? = null,
)