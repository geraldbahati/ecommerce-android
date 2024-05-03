package com.example.ecommerceapp.features.catalog.presentation.categories.category_detail


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.features.catalog.domain.models.SubCategory
import com.example.ecommerceapp.features.catalog.presentation.categories.CategoriesEvent
import com.example.ecommerceapp.features.catalog.presentation.categories.CategoriesViewModel
import com.example.ecommerceapp.ui.theme.LocalSpacing
import com.example.ecommerceapp.ui.theme.dh
import com.example.ecommerceapp.ui.theme.dw
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(navGraph = "HomeNavGraph")
@Composable
fun CategoryDetailScreen(
    navigator: DestinationsNavigator,
    categoriesViewModel: CategoriesViewModel,
) {
    val state by categoriesViewModel.state.collectAsState()


    val spacing = LocalSpacing.current

    Scaffold(
        topBar = {
            CategoryDetailTopBar(
                modifier = Modifier.padding(horizontal = 0.02.dw),
                onBack = { navigator.navigateUp() },
                onCart = { /* TODO: Navigate to cart */ }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .navigationBarsPadding()
                    .fillMaxSize(),
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Subcategory list
                    SubCategoryList(
                        categoryTitle = state.selectedCategory?.name ?: "Category",
                        subCategories = state.subCategories,
                        selectedSubCategory = state.selectedSubCategory,
                        onEvent = { event ->
                            categoriesViewModel.onEvent(event)
                        }
                    )

                    Spacer(modifier = Modifier.height(spacing.huge))

                    // Product list
                    ProductList(
                        modifier = Modifier.padding(horizontal = 0.072.dw),
                        products = state.products
                    )
                }

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CategoryDetailTopBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onCart: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = "Category",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                )
            )
        },

        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },

        actions = {
            IconButton(onClick = onCart) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cart),
                    contentDescription = "Cart",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
}


@Composable
private fun SubCategoryList(
    modifier: Modifier = Modifier,
    categoryTitle: String,
    subCategories: List<SubCategory>,
    selectedSubCategory: SubCategory? = null,
    onEvent: (CategoriesEvent) -> Unit
) {
    val spacing = LocalSpacing.current

    val listState = rememberLazyListState()


    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(0.008.dh))

        // Title and filter
        Row(
            modifier = Modifier
                .padding(horizontal = 0.072.dw, vertical = 0.008.dh)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // title
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chair),
                    contentDescription = "Category",
                    modifier = Modifier
                        .padding(end = spacing.medium)
                        .size(24.dp),
                )

                Text(
                    text = categoryTitle,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        lineHeight = 28.sp
                    )
                )
            }

            // filter
            Row(
                modifier = Modifier
                    .clickable(onClick = { /* TODO: Navigate to filter */ }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Filter",
                    modifier = Modifier
                        .padding(end = spacing.small)
                        .size(16.dp),
                )

                Text(
                    text = "Filter",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(0.016.dh))

        // Subcategory list
        LazyRow(
            state = listState,
            content = {
                itemsIndexed(
                    items = subCategories,
                    key = { _, item -> item.id }
                ) { index, subCategory ->
                    // Subcategory item
                    SubCategoryOptionButton(
                        modifier = Modifier.padding(
                            start = if (index == 0) 0.072.dw else 0.dp,
                            end = if (index == subCategories.size - 1) 0.072.dw else spacing.small
                        ),
                        subCategory = subCategory,
                        isSelected = selectedSubCategory?.id == subCategories[index].id,
                        onClick = { selectedSubCategory ->
                            onEvent(CategoriesEvent.OnSubCategorySelected(selectedSubCategory))
                        }
                    )
                }
            }
        )
    }
}


@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    products: List<Product> = emptyList()
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(0.41.dw),
        verticalItemSpacing = 0.03.dh,
        horizontalArrangement = Arrangement.spacedBy(0.03.dw),
        content = {
            val specialSequence = generateSpecialSequence(products.size - 1)

            itemsIndexed(
                items = products,
                key = { _, item -> item.id }
            ) { index, product ->
                // Use the sequence to determine the height
                ProductItem(
                    modifier = Modifier
                        .height(if (index in specialSequence) 0.24.dh else 0.27.dh),
                    product = product
                )
            }
        }
    )
}

fun generateSpecialSequence(maxIndex: Int): List<Int> {
    val sequence = mutableListOf<Int>()
    var current = 0
    while (current <= maxIndex) {
        sequence.add(current)
        // start with +3, +1, +3, +1...
        current += if ((sequence.size - 1) % 2 == 0) 3 else 1
    }
    return sequence
}