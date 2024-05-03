package com.example.ecommerceapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.NavGraphs
import com.example.ecommerceapp.features.catalog.data.sync.DataSyncWorker
import com.example.ecommerceapp.features.catalog.presentation.bottomnav.MainApp
import com.example.ecommerceapp.features.catalog.presentation.categories.CategoriesViewModel
import com.example.ecommerceapp.features.catalog.presentation.categories.category_detail.CategoryDetailScreen
import com.example.ecommerceapp.features.catalog.presentation.categories.category_list.CategoriesScreen
import com.example.ecommerceapp.features.catalog.presentation.categories.search.SearchScreen
import com.example.ecommerceapp.features.destinations.CategoriesScreenDestination
import com.example.ecommerceapp.features.destinations.CategoryDetailScreenDestination
import com.example.ecommerceapp.features.destinations.SearchScreenDestination
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import com.example.ecommerceapp.ui.theme.initSize
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSize()

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT, Color.TRANSPARENT
            )
        )
        schedulePeriodicDataSync()
        setContent {
            EcommerceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    val categoriesViewModel = hiltViewModel<CategoriesViewModel>()

                    DestinationsNavHost(navGraph = NavGraphs.root)
//                    {
//                        composable(CategoriesScreenDestination) {
//                            CategoriesScreen(
//                                navigator = destinationsNavigator,
//                                categoriesViewModel = categoriesViewModel
//                            )
//                        }
//
//                        composable(CategoryDetailScreenDestination) {
//                            CategoryDetailScreen(
//                                navigator = destinationsNavigator,
//                                categoriesViewModel = categoriesViewModel
//                            )
//                        }
//
//                        composable(SearchScreenDestination) {
//                            SearchScreen(
//                                navigator = destinationsNavigator,
//                                categoriesViewModel = categoriesViewModel
//                            )
//                        }
//                    }
                }
            }
        }

    }

    private fun schedulePeriodicDataSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncWorkRequest = PeriodicWorkRequestBuilder<DataSyncWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            Constants.DATA_SYNC_WORKER_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            syncWorkRequest
        )
    }
}
