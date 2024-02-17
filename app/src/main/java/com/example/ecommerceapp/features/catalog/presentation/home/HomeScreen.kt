package com.example.ecommerceapp.features.catalog.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ecommerceapp.R
import com.example.ecommerceapp.config.Routes
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(route = Routes.HOME)
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val state by homeViewModel.state.collectAsState()
    homeViewModel.onEvent(HomeEvent.OnSetNavigator(navigator))

    Scaffold (
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
                 Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                 ) {
                     Text(
                         modifier = Modifier.weight(1f),
                         text = "Discover",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 22.sp,
                                lineHeight = 28.sp
                            )
                     )
                     Icon(
                         painter = painterResource(id = R.drawable.ic_cart),
                         contentDescription = "Cart",
                         tint = MaterialTheme.colorScheme.onBackground
                        )
                 }
        },
        content = {
            paddingValues -> LazyColumn(
            modifier = Modifier.padding(paddingValues),
            content = {
                item {
                    NewsCard(modifier = Modifier
                        .height(200.dp)
                        .padding(top = 12.dp, bottom = 21.dp, start = 20.dp, end = 20.dp),type = "", title = "", imageUrl = "", description = "hello") {
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),

                    ){
                        SectionHeader(
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                            title = "Categories",
                            onClick = {
                                //TODO navigate to new arrivals
                            }
                        )

                        Spacer(modifier = Modifier.height(18.dp))
                        
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            content = {
                                itemsIndexed(
                                    state.loadedCategories,
                                    key = { _, category -> category.id }
                                ){
                                    index, category ->
                                    if(index == 0) {
                                        Spacer(modifier = Modifier.width(20.dp))
                                    }
                                    CategoryCard(
                                        modifier = Modifier
                                            .padding(end = 16.5.dp),
                                        isSelected = state.selectedCategory?.id == category.id,
                                        category = category,
                                        imageId = R.drawable.ic_chair,
                                        onClick = {
                                            selectedCategory -> homeViewModel.onEvent(HomeEvent.OnCategorySelected(selectedCategory))
                                        }
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),

                        content = {


                                SectionHeader(
                                    title = "Trendings",
                                    onClick = {
                                        //TODO navigate to new arrivals
                                    }
                                )

                                Spacer(modifier = Modifier.height(22.dp))

                            for (i in 0..4) {
                                if (i != 4) {
                                    ProductCard(
                                        modifier = Modifier
                                            .padding(bottom = 20.dp),
                                        product = "New Arrivals Winter",
                                        onClick = {
                                            //TODO navigate to product
                                        }
                                    )

                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth(.60f)
                                            .padding(top = 10.dp, bottom = 20.dp)
                                            .align(Alignment.End),
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f)
                                    )
                                } else {
                                    ProductCard(
                                        modifier = Modifier
                                            .padding(bottom = 20.dp),
                                        product = "New Arrivals Winter",
                                        onClick = {
                                            //TODO navigate to product
                                        }
                                    )
                                    Spacer(modifier = Modifier.height(30.dp))
                                }
                            }

                        }
                    )
                }

                item{
                    NewsCard(
                        modifier = Modifier
                            .height(200.dp)
                            .padding(bottom = 21.dp, start = 20.dp, end = 20.dp),
                        type = "", title = "", imageUrl = "") {

                    }
                }
            }
        )
        }
    )
}

@Composable
private fun SectionHeader(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
){
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                lineHeight = 25.sp
            )
        )
        Box (
            modifier = Modifier
                .clickable(onClick = onClick),
            contentAlignment = Alignment.CenterEnd
        ){
            Text(
                text = "VIEW ALL",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}