package com.example.ecommerceapp.features.catalog.presentation.categories.productview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.features.catalog.presentation.categories.category_detail.ProductItem
import com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget.ColourDotOption
import com.example.ecommerceapp.ui.theme.dh
import com.example.ecommerceapp.widgets.CustomButton
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun ProductDetailsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    item(key = "product_image") {
                        ProductImages(
                            modifier = Modifier
                                .height(380.dp)
                                .background(Color.Gray)
                                .fillMaxWidth()
                        )
                    }

                    item(key = "product_content") {
                        ProductContent(
                            modifier = Modifier
                                .offset(y = (-32).dp)
                                .fillMaxWidth()
                        )
                    }
                }

                BottomNavBar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
        }
    )
}


@Composable
private fun BottomNavBar(modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .height(88.dp)
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(MaterialTheme.colorScheme.surface)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Quantity stepper
            NumericStepper(
                value = 7,
                onValueChange = { /* Handle value change */ },
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Add to cart button
            CustomButton(
                title = "Add to Cart",
                onClick = { /* Handle add to cart */ },
                modifier = Modifier.weight(1f)
            )

        }
    }
}

@Composable
fun NumericStepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))

    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Text
            val valueText = when {
                value < 10 -> "0$value"
                else -> value.toString()
            }

            TextField(
                value = valueText,
                onValueChange = { /* Handle value change */ },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .width(32.dp)
                    .height(32.dp),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Transparent
                ),
                singleLine = true,
                readOnly = true
            )

            // buttons
            Column(
                verticalArrangement = Arrangement.SpaceAround

            ){
                IconButton(
                    onClick = { onValueChange(value - 1) },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropUp,
                        contentDescription = "Increase",
                    )
                }

                IconButton(
                    onClick = { onValueChange(value + 1) },
                    modifier = Modifier.size(24.dp)
                    ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Decrease"
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
private fun ProductDetailsAppBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean = false
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back button
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "Back"
                )
            }

            // Favorite button
            IconButton(onClick = onFavoriteClick) {
                if (isFavorite) {
                    // Show filled heart icon
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        tint = Color.Red
                    )
                } else {
                    // Show outlined heart icon
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductImages(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
//            .background(Color.Gray)
    ) {
        // Product images

        // Product details app bar
        ProductDetailsAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            onBackClick = {},
            onFavoriteClick = {},
            isFavorite = false
        )
    }
}

@Composable
private fun ProductDetails(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // title
        Text(
            text = "Casey 1 seater Manual Recliner in Brown Colour",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // price and rating
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val price = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 15.sp)) {
                    append("$")
                }
                withStyle(style = SpanStyle(fontSize = 20.sp)) {
                    append("299")
                }
            }


            Text(
                text = price,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
            )

            // Rating
            RatingBar(rating = 3.5f, onRatingChange = {})
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Colours
        ColourDetails(
            colours = listOf("#89675E", "#244395", "#D99B61", "#D4D7D9", "#0B2E40")
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Description
        DescriptionSection(
            description = "Design is an evolutionary process, and filler text is just one tool in " +
                    "your progress-pushing arsenal. Relax and do whatever fits with your design process." +
                    " Don’t set a lot of restrictive hard-and-fast rules distract designers and design."
        )

        // Composition and Care
        CustomDropDown(
            modifier = Modifier.padding(top = 16.dp),
            title = "Composition & Care",
            items = listOf(
                "Composition: 100% Polyester",
                "Care: Wipe with a clean, dry cloth when needed"
            ),
            onClick = {}
        )


        // Shipping and Returns
        CustomDropDown(
            modifier = Modifier.padding(top = 16.dp),
            title = "Shipping & Returns",
            items = listOf(
                "Shipping: Free",
                "Returns: Within 7 days of delivery"
            ),
            onClick = {}
        )

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // reviews
        Column {
            // title
            Text(
                text = "Reviews",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.SemiBold
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Rating
                Text(
                    text = "248 reviews",
                    style = MaterialTheme.typography.bodyMedium
                )

                // Write a review
                val ratingText = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 17.sp)) {
                        append("4.8")
                    }
                    append(" out of 5.0")
                }

                Text(
                    text = ratingText,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                )
            }

        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // similar products
//        SimilarProducts()

        // quantity and add to cart button
    }
}

@Composable
fun ProductContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
            shadowElevation = 8.dp,
            color = MaterialTheme.colorScheme.background
        ) {

            ProductDetails(
                modifier = Modifier
                    .padding(top = 16.dp)
            )
        }
    }
}


@Composable
fun RatingBar(rating: Float, maxRating: Int = 5, onRatingChange: (Float) -> Unit) {
    Row {
        for (i in 1..maxRating) {
            val icon = when {
                i <= rating -> Icons.Filled.Star
                i - 0.5f <= rating -> Icons.AutoMirrored.Filled.StarHalf
                else -> Icons.Filled.StarBorder
            }
            val description = when (icon) {
                Icons.Filled.Star -> "Filled Star"
                Icons.AutoMirrored.Filled.StarHalf -> "Half Filled Star"
                else -> "Outlined Star"
            }
            Icon(
                imageVector = icon,
                contentDescription = description,
                tint = if (i <= rating + 0.5) Color(0xFFFFC107) else Color.Gray,
                modifier = Modifier
                    .clickable { onRatingChange(i.toFloat()) }
            )
        }
    }
}

@Composable
private fun ColourDetails(colours: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        // title
        Text(
            text = "Colours",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold
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
private fun DescriptionSection(description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        // title
        Text(
            text = "Description",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold
            ),
        )

        Spacer(modifier = Modifier.height(8.dp))

        // description
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )


        Spacer(modifier = Modifier.height(32.dp))

        DescriptionDetails(title = "Categories", items = listOf("Furniture", "Accessories"))
        Spacer(modifier = Modifier.height(8.dp))
        DescriptionDetails(title = "Tags", items = listOf("#Furniture", "#Chair", "#Table"))
    }
}

@Composable
private fun DescriptionDetails(
    modifier: Modifier = Modifier,
    title: String,
    items: List<String>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        // title
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 13.sp,
            ),
            modifier = Modifier.width(80.dp)
        )

        Text(
            text = ":",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        // list of items
        val itemsText = items.joinToString(", ")
        Text(
            text = itemsText,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 13.sp,
                lineHeight = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
private fun CustomDropDown(
    modifier: Modifier = Modifier,
    title: String,
    items: List<String>,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        // title
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Dropdown",
                modifier = Modifier.rotate(if (isSelected) 90f else 0f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                )
            )
        }


        // list of items
        if (isSelected) {
            Spacer(modifier = Modifier.height(16.dp))

            items.forEach { item ->
                val wordList = item.split(":")

                val itemText = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold)) {
                        append(wordList[0])
                    }
                    append(": ${wordList[1]}")
                }

                Text(
                    text = itemText,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 13.sp,
                    )
                )
            }
        }
    }
}

@Composable
private fun SimilarProducts(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        // title
        Text(
            text = "Similar Products",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.SemiBold
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // list of products
        ProductGrid(
            products = listOf(
                Product(
                    id = "1",
                    name = "Casey 1 seater Manual Recliner in Brown Colour",
                    imageURL = "",
                    description = "Design is an evolutionary process, and filler text is just one tool in " +
                            "your progress-pushing arsenal. Relax and do whatever fits with your design process." +
                            " Don’t set a lot of restrictive hard-and-fast rules distract designers and design.",
                    price = 299.0,
                    stock = 10,
                    subCategoryId = "1",
                    rating = 4.8,
                    reviewCount = 248,
                    discountRate = "10%",
                    keywords = "Furniture, Chair, Table"
                ),
                Product(
                    id = "2",
                    name = "Casey 1 seater Manual Recliner in Brown Colour",
                    imageURL = "",
                    description = "Design is an evolutionary process, and filler text is just one tool in " +
                            "your progress-pushing arsenal. Relax and do whatever fits with your design process." +
                            " Don’t set a lot of restrictive hard-and-fast rules distract designers and design.",
                    price = 299.0,
                    stock = 10,
                    subCategoryId = "1",
                    rating = 4.8,
                    reviewCount = 248,
                    discountRate = "10%",
                    keywords = "Furniture, Chair, Table"
                ),
                Product(
                    id = "3",
                    name = "Casey 1 seater Manual Recliner in Brown Colour",
                    imageURL = "https://images.unsplash.com/photo-1612830720309-4b3b3b3b3b3b",
                    description = "Design is an evolutionary process, and filler text is just one tool in " +
                            "your progress-pushing arsenal. Relax and do whatever fits with your design process." +
                            " Don’t set a lot of restrictive hard-and-fast rules distract designers and design.",
                    price = 299.0,
                    stock = 10,
                    subCategoryId = "1",
                    rating = 4.8,
                    reviewCount = 248,
                    discountRate = "10%",
                    keywords = "Furniture, Chair, Table"
                ),

            )
        )
    }

}

@Composable
fun ProductGrid(
    modifier: Modifier = Modifier,
    products: List<Product>
) {
    // Assuming you want two columns
    Column(modifier = modifier) {
        for (i in products.indices step 2) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                ProductItem(
                    product = products[i],
                    modifier = Modifier
                        .weight(1f)
                        .height(0.24.dh)
                )

                if (i + 1 < products.size) {
                    Spacer(modifier = Modifier.width(8.dp))

                    ProductItem(
                        product = products[i + 1],
                        modifier = Modifier
                            .weight(1f)
                            .height(0.24.dh)
                    )
                } else {
                    Spacer(modifier = Modifier
                        .weight(1f)
                        .height(0.24.dh))
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailsScreenPreview() {
    ProductDetailsScreen()
}