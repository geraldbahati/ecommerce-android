package com.example.ecommerceapp.features.catalog.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    type: String,
    title: String,
    description : String = "",
    imageUrl: String,
    onClick: () -> Unit,
) {
    Card (
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        onClick = onClick
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
//             NewsCardImage(imageUrl = imageUrl)
            // gradient overlay

            // check for description
            if (description.isNotEmpty()) {
                ContentWithDescription(
                    type = type,
                    title = title,
                    description = description
                )
            } else {
                ContentWithoutDescription(
                    type = type,
                    title = title
                )
            }
        }
    }
}

@Composable
fun ContentWithDescription(
    type: String,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.padding(11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
        ){
            // type
            Text(
                modifier = Modifier.padding(bottom = 3.dp),
                text = "Introducing",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 12.sp
                ),
                color = MaterialTheme.colorScheme.onSecondary
            )

            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = "Budget Furniture",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 12.sp
                ),
                color = MaterialTheme.colorScheme.onSecondary
            )

            Text(
                text = "All Furniture Discount",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 11.sp
                ),
                color = MaterialTheme.colorScheme.onSecondary
            )
        }

        Column(
            modifier = Modifier.padding(end = 8.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ){
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    modifier = Modifier.padding(end = 4.dp, bottom = 2.dp),
                    text = "Upto",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 12.sp
                    ),
                    color = MaterialTheme.colorScheme.onSecondary
                )

                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = "50%",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 17.sp,
                    ),
                    color = MaterialTheme.colorScheme.onSecondary
                )

                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = "Off",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 12.sp
                    ),
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(80.dp)
                    .height(34.dp)
                    .padding(top = 7.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                contentPadding = PaddingValues(0.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Shop Now",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 13.sp
                    )
                )
            }
        }
    }
}

@Composable
fun ContentWithoutDescription(
    type: String,
    title: String
) {
    Column(
        modifier = Modifier
            .padding(start = 21.dp, top = 19.dp, end = 21.dp, bottom = 15.dp)
            .fillMaxWidth(0.48f),
        horizontalAlignment = Alignment.Start
    ) {
        // type
        Text(
            text = "Collections",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 13.sp,
                lineHeight = 16.sp
            ),
            color = MaterialTheme.colorScheme.onSecondary
        )

        Text(
            modifier = Modifier.padding(bottom = 20.dp),
            text = "New Arrivals Winter",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 17.sp,
                lineHeight = 22.sp
            ),
            color = MaterialTheme.colorScheme.onSecondary
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
        ){
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.TopStart
            ){
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "UPTO",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 12.sp
                        ),
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "35%",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 20.sp
                        ),
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                    Text(
                        text = "OFF*",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 12.sp
                        ),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(100.dp)
                    .height(34.dp),
//                    .padding(top = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                contentPadding = PaddingValues(0.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Shop Now",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 13.sp
                    )
                )
            }
        }

    }
}