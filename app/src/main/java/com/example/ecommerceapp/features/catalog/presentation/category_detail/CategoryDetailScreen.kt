package com.example.ecommerceapp.features.catalog.presentation.category_detail


import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.features.catalog.presentation.widget.OptionOutlineButton
import com.example.ecommerceapp.ui.theme.LocalSpacing
import com.example.ecommerceapp.ui.theme.dh
import com.example.ecommerceapp.ui.theme.dw
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(
    navArgsDelegate = CategoryDetailScreenNavArgs::class
)
@Composable
fun CategoryDetailScreen(
    destination: DestinationsNavigator
) {
    val spacing = LocalSpacing.current

    Scaffold(
        topBar = {
            CategoryDetailTopBar(
                modifier = Modifier.padding(horizontal = 0.02.dw),
                onBack = { destination.navigateUp() },
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

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    // Subcategory list
                    SubCategoryList()

                    Spacer(modifier = Modifier.height(spacing.huge))

                    // Product list
                    ProductList(modifier = Modifier.padding(horizontal = 0.072.dw))
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
) {
    val spacing = LocalSpacing.current

    Column (
        modifier = modifier.fillMaxWidth()
    ){
        Spacer(modifier = Modifier.height(0.008.dh))

        // Title and filter
        Row (
            modifier = Modifier
                .padding(horizontal = 0.072.dw, vertical = 0.008.dh)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // title
            Row (
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
                    text = "Books",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 22.sp,
                        lineHeight = 28.sp
                    )
                )
            }

            // filter
            Row (
                modifier = Modifier
                    .clickable(onClick = { /* TODO: Navigate to filter */ }),
                verticalAlignment = Alignment.CenterVertically
            ){
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
            content = {
                itemsIndexed(
                    items = listOf("All", "Fiction", "Non-Fiction", "Children", "Science", "Art", "History", "Biography", "Cooking", "Travel"),
                    key = { _, item -> item }
                ) { index, item ->
                    // Subcategory item
                    OptionOutlineButton(
                        modifier = Modifier.padding(
                            start = if (index == 0) 0.072.dw else 0.dp,
                            end = if (index == 9) 0.072.dw else spacing.small
                        ),
                        title = item,
                        onClick = {
                        /* TODO: Navigate to subcategory */
                            Log.i("CategoryDetailScreen", "Subcategory: $item")
                        }
                    )
                }
            }
        )
    }
}


@Composable
fun ProductList(
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(0.41.dw),
        verticalItemSpacing = 0.03.dh,
        horizontalArrangement = Arrangement.spacedBy(0.03.dw),
        content = {
            val itemsList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25")
            val specialSequence = generateSpecialSequence(itemsList.size - 1)

            itemsIndexed(
                items = itemsList,
                key = { _, item -> item }
            ) { index, item ->
                // Use the sequence to determine the height
                ProductItem(
                    modifier = Modifier
                        .height(if (index in specialSequence) 0.24.dh else 0.27.dh),
                    title = "Product $item",
                    price = "$${index * 10}",
                    imageUrl = "https://cdn.vox-cdn.com/thumbor/YDX2_jc6LlEumMk5eggV1ygGBm8=/0x0:1076x599/1200x628/filters:focal(538x300:539x301)/cdn.vox-cdn.com/uploads/chorus_asset/file/20030737/xWZMNYm.png"
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