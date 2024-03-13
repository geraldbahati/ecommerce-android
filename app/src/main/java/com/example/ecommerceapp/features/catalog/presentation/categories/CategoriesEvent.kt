package com.example.ecommerceapp.features.catalog.presentation.categories

import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.features.catalog.domain.models.SubCategory

sealed class CategoriesEvent {
    // events for categories
    data class OnCategorySelected(val category: Category): CategoriesEvent()
    data object OnLoadCategories: CategoriesEvent()

    // events for sub categories
    data class OnSubCategorySelected(val subCategory: SubCategory): CategoriesEvent()


    // search events
    data class OnSearchQueryChange(val query: String): CategoriesEvent()
    data object OnSearchCategory: CategoriesEvent()
    data class OnSearchCategorySelected(val category: Category): CategoriesEvent()
    data class OnToggleSearch(val isSearching: Boolean): CategoriesEvent()

    // products events
    data class OnProductSelected(val product: Product): CategoriesEvent()
    data object OnLoadProducts: CategoriesEvent()
}