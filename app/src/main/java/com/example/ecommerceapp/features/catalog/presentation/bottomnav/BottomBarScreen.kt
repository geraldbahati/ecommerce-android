package com.example.ecommerceapp.features.catalog.presentation.bottomnav

import com.example.ecommerceapp.features.destinations.CategoriesScreenDestination
import com.example.ecommerceapp.features.destinations.DirectionDestination
import com.example.ecommerceapp.features.destinations.HomeScreenDestination

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {
    // Home
    data object Home: BottomBarScreen(
        route = "home_screen",
        title = "Home",
        icon = com.example.ecommerceapp.R.drawable.ic_home,
        icon_focused = com.example.ecommerceapp.R.drawable.ic_home_focused
    )

    // Catalog
    data object Categories: BottomBarScreen(
        route = "categories_screen",
        title = "Search",
        icon = com.example.ecommerceapp.R.drawable.ic_search_nav,
        icon_focused = com.example.ecommerceapp.R.drawable.ic_search_focused
    )
}