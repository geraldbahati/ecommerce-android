package com.example.ecommerceapp.features.catalog.presentation.categories

import com.example.ecommerceapp.features.catalog.domain.models.Category

sealed class CategoriesEvent {
    data class OnCategorySelected(val category: Category): CategoriesEvent()
    data object OnLoadCategories: CategoriesEvent()
}