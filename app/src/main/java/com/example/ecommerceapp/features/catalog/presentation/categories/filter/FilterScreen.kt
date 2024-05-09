package com.example.ecommerceapp.features.catalog.presentation.categories.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.config.Constants
import com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget.ColourDotOption
import com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget.FilterItem
import com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget.MoreButton
import com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget.PriceRangeSlider
import com.example.ecommerceapp.widgets.CustomButton
import com.ramcosta.composedestinations.annotation.Destination

@Destination(navGraph = Constants.NAV_GRAPH_HOME)
@Composable
fun FilterScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            FilterTopBar()
        },
        content = { paddingValues ->
            FilterContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterTopBar() {
    // TopBar
    TopAppBar(
        title = {
            Text(
                text = "Filter",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 22.sp,
                    lineHeight = 28.sp,
                )
            )
        },
        actions = {
            /// Cancel Button
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
        },
    )
}

@Composable
fun FilterContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
            .padding(bottom = 16.dp),
//            .padding(horizontal = 0.072.dw),
    ) {
        // scrollable content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            // sort by
            item(
                key = "Sort By"
            ) {
                SortByFilter()
            }

            // material
            item(
                key = "Material"
            ) {
                GenericFilter(
                    title = "Material",
                    items = listOf(
                        "Cotton",
                        "Silk",
                        "Polyester",
                        "Wool",
                        "Linen",
                        "Denim",
                        "Leather"
                    ),
                    columns = 3,
                    maxVisibleItems = 6
                )
            }

            // categories
            item(
                key = "Categories"
            ) {
                GenericFilter(
                    title = "Categories",
                    items = listOf(
                        "Tops",
                        "Bottoms",
                        "Dresses",
                        "Outerwear",
                        "Shoes",
                        "Accessories",
                        "Bags"
                    ),
                    columns = 3,
                    maxVisibleItems = 6
                )
            }

            // colour
            item(
                key = "Colour"
            ) {
                ColourFilter(
                    colours = listOf(
                        "#000000",
                        "#661112",
                        "#FF0000",
                        "#00FF00",
                        "#0000FF",
                        "#FFFF00",
                        "#00FFFF",
                        "#FF00FF"
                    )
                )
            }

            // price
            item(
                key = "Price"
            ) {
                PriceRangeSlider(
                    minPrice = 0f,
                    maxPrice = 1000f,
                    initialMinPrice = 100f,
                    initialMaxPrice = 900f
                ) {
                    // Handle price range change
                }
            }
        }

        // apply button
        CustomButton(
            title = "Apply",
            onClick = { /* Handle apply button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(horizontal = 16.dp),
        )
    }
}

@Composable
fun SortByFilter() {
    Column(
        modifier = Modifier
            .padding(top = 32.dp, bottom = 16.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 12.dp)
            ) {
                Text(
                    text = "Sort By",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 15.sp,
                        lineHeight = 20.sp
                    ),
                    textAlign = TextAlign.Start
                )
            }

            // sort by dropdown
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Sort By"
                )
            }
        }
        // Selected option
        Box(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Text(
                text = "Recommended",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 11.sp,
                    lineHeight = 13.sp
                ),
                textAlign = TextAlign.Start
            )
        }

    }
}

@Composable
fun ColourFilter(colours: List<String>) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
    ) {
        // title
        Text(
            text = "Colour",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 15.sp,
                lineHeight = 20.sp
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // list of colours
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(colours) { colour ->
                ColourDotOption(
                    colour = colour,
                    isSelected = false,
                    onColourSelected = { /* Handle colour selection */ },
                    activeColor = MaterialTheme.colorScheme.background
                )

            }
        }
    }
}

@Composable
fun GenericFilter(
    title: String,
    items: List<String>,
    columns: Int = 3,
    maxVisibleItems: Int = 5
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp, lineHeight = 20.sp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp, max = 200.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val showMoreButton = items.size > maxVisibleItems - 1
                items(if (showMoreButton) maxVisibleItems - 1 else items.size) { index ->
                    FilterItem(
                        item = items[index],
                        isSelected = false
                    ) { /* Handle item selection */ }
                }
                if (showMoreButton) {
                    item { MoreButton(onItemSelected = { /* Handle "more" button click */ }) }
                }
            }
        }
    }
}


@Preview
@Composable
fun FilterScreenPreview() {
    FilterScreen()
}