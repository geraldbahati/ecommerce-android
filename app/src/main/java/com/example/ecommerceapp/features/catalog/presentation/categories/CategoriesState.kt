package com.example.ecommerceapp.features.catalog.presentation.categories

import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.features.catalog.domain.models.SubCategory

data class CategoriesState(
    // categories state
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val selectedCategory: Category? = null,

    // sub categories state
    val subCategories: List<SubCategory> = emptyList(),
    val selectedSubCategory: SubCategory? = null,

    // search state
    val isSearching: Boolean = false,
    val loadedCategories: List<Category> = emptyList(),
    val selectedSearchCategory: Category? = null,
    val query: String = "",
    val isLoadingCategories: Boolean = false,

    // products state
    val products: List<Product> = emptyList(),
    val selectedProduct: Product? = null,
    val isLoadingProducts: Boolean = false,

)