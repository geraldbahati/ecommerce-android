package com.example.ecommerceapp.features.catalog.presentation.home

import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

sealed class HomeEvent{
    data class OnCategorySelected(val category: Category): HomeEvent()
    data class OnSetNavigator(val navigator: DestinationsNavigator): HomeEvent()

    data object OnLoadCategories: HomeEvent()
    data object OnNavigateToCart: HomeEvent()
    data object OnNavigateToCategories: HomeEvent()

}