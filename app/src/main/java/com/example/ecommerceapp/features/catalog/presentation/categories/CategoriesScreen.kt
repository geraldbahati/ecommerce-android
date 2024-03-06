package com.example.ecommerceapp.features.catalog.presentation.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.features.catalog.presentation.widget.CategoryTab
import com.example.ecommerceapp.ui.theme.LocalSpacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CategoriesScreen(
    navigator: DestinationsNavigator,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {

    val state by categoriesViewModel.state.collectAsState()


    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),

        topBar = {
            CategoriesAppBar(
                navigator = navigator
            )
        },

        content = { paddingValues ->
            ScreenContent(
                modifier = Modifier.padding(paddingValues),
                categories = state.categories,
                selectedCategory = state.selectedCategory,
                onEvent = { event -> categoriesViewModel.onEvent(event) }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoriesAppBar(
    navigator: DestinationsNavigator
) {
    val spacing = LocalSpacing.current

    TopAppBar(
        modifier = Modifier.padding(horizontal = spacing.medium),
        title = {},
        navigationIcon = {
            IconButton(onClick = { navigator.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        actions = {
            IconButton(onClick = {
//                navigator.navigate(Routes.SEARCH_CATEGORY)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
         }
    )
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    selectedCategory: Category?,
    onEvent: (CategoriesEvent) -> Unit
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.medium),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(start = spacing.medium),
                text = "Categories",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.size(spacing.medium))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                itemsIndexed(
                    categories,
                    key = { _, category -> category.id }
                ) {
                        _, category ->
                    CategoryTab(
                        isSelected =  selectedCategory?.id == category.id,
                        category = category,
                        onClick = {
                            selectedCategory -> onEvent(CategoriesEvent.OnCategorySelected(selectedCategory))

                            //TODO navigate to category
                        }
                    )
                    CategoryTab(
                        isSelected =  selectedCategory?.id == category.id,
                        category = category,
                        onClick = {
                                selectedCategory -> onEvent(CategoriesEvent.OnCategorySelected(selectedCategory))

                            //TODO navigate to category
                        }
                    )
                }
            }
        }
    }
}