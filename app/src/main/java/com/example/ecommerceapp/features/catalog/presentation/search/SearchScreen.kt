package com.example.ecommerceapp.features.catalog.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.features.catalog.presentation.home.HomeEvent
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state by searchViewModel.searchState.collectAsState()
    searchViewModel.onEvent(SearchEvent.OnSetNavigator(navigator))

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                TopAppBar(
                    title = {
                        Text(
                        text = "Discover",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 22.sp,
                            lineHeight = 28.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                searchViewModel.onEvent(SearchEvent.OnBackClicked)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back to previous screen"
                            )
                        }
                    }
                )
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 20.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
//                ) {
//                    Text(
//                        modifier = Modifier.fillMaxWidth(),
//                        text = "Discover",
//                        style = MaterialTheme.typography.titleLarge.copy(
//                            fontSize = 22.sp,
//                            lineHeight = 28.sp,
//                            textAlign = TextAlign.Center
//                        )
//                    )
//
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_back),
//                        contentDescription = "Back to previous screen",
//                        modifier = Modifier
//                            .clickable {
//                                searchViewModel.onEvent(SearchEvent.OnBackClicked)
//                            }
//                            .padding(start = 10.dp),
//                    )
//                }

                // search bar

            }
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            // search bar
            // products
//            state.loadedProducts.forEach { product ->
//                ProductCard(
//                    product = product,
//                    onClick = {
//                        searchViewModel.onEvent(SearchEvent.OnProductClicked(product))
//                    }
//                )
//            }
        }
    }
}