package com.example.ecommerceapp.features.catalog.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.features.catalog.domain.models.Category

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: String,
    onClick: (id:String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        // profile image
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(90.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = MaterialTheme.shapes.medium
                )
        )

        Spacer(modifier = Modifier.width(20.dp))

        // product details
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = "Armchair Konna Green",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(17.dp))

            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "$",
                        style = MaterialTheme.typography.labelMedium
                    )
                    Text(
                        text = "29.00",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 13.sp
                        )
                    )
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(100.dp)
                        .height(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    contentPadding = PaddingValues(0.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "SHOP",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    )
                }
            }
        }
    }
}