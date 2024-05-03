package com.example.ecommerceapp.features.catalog.presentation.bottomnav

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.features.NavGraphs
import com.example.ecommerceapp.features.catalog.presentation.categories.CategoriesViewModel
import com.example.ecommerceapp.features.catalog.presentation.categories.category_detail.CategoryDetailScreen
import com.example.ecommerceapp.features.catalog.presentation.categories.category_list.CategoriesScreen
import com.example.ecommerceapp.features.catalog.presentation.categories.search.SearchScreen
import com.example.ecommerceapp.features.catalog.presentation.home.HomeScreen
import com.example.ecommerceapp.features.destinations.CategoriesScreenDestination
import com.example.ecommerceapp.features.destinations.CategoryDetailScreenDestination
import com.example.ecommerceapp.features.destinations.HomeScreenDestination
import com.example.ecommerceapp.features.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MainApp() {
    val navController = rememberNavController()
    val categoriesViewModel = hiltViewModel<CategoriesViewModel>()

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            BottomBar( navController = navController)
        }
    ){innerPadding ->
        DestinationsNavHost(
            navGraph = NavGraphs.HomeNavGraph,
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(HomeScreenDestination) {
                HomeScreen(navigator = destinationsNavigator)
            }
            composable(CategoriesScreenDestination) {
                CategoriesScreen(
                    navigator = destinationsNavigator,
                    categoriesViewModel = categoriesViewModel
                )
            }

            composable(CategoryDetailScreenDestination) {
                CategoryDetailScreen(
                    navigator = destinationsNavigator,
                    categoriesViewModel = categoriesViewModel
                )
            }

            composable(SearchScreenDestination) {
                SearchScreen(
                    navigator = destinationsNavigator,
                    categoriesViewModel = categoriesViewModel
                )
            }

        }
    }
}

@Composable
fun BottomBar(
//    navigator: DestinationsNavigator,
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Categories
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .background(Color.Transparent)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
//                navigator = navigator
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
//    navigator: DestinationsNavigator
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.6f) else Color.Transparent

    val contentColor =
        if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground

    Box(
        modifier = Modifier
            .height(40.dp)
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = {
//                when(screen){
//                    BottomBarScreen.Home -> navigator.navigate(HomeScreenDestination) {
//                        popUpTo(NavGraphs.HomeNavGraph.startDestination.route) { inclusive = true }
//                        launchSingleTop = true
//                    }
//                    BottomBarScreen.Catalog -> navigator.navigate(CategoriesScreenDestination) {
//                        popUpTo(NavGraphs.HomeNavGraph.startDestination.route) { inclusive = true }
//                        launchSingleTop = true
//                    }
//                }
                Log.i("test","AddItem: ${screen.route}")
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {


            //* if menu title : Report means we will show badge
            if(screen.title=="Report"){ // with badge
                BadgedBox(badge = { Badge { Text("6") } }) {
                    Icon(
                        painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                        contentDescription = "icon",
                        tint = contentColor
                    )
                }

            }
            else{

                Icon(
                    painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                    contentDescription = "icon",
                    tint = contentColor
                )

            }






            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = contentColor
                )
            }
        }
    }
}

