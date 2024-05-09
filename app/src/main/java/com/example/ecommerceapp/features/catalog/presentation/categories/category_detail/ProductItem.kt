package com.example.ecommerceapp.features.catalog.presentation.categories.category_detail


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ecommerceapp.R
import com.example.ecommerceapp.features.catalog.domain.models.Product
import com.example.ecommerceapp.ui.theme.dh
import com.example.ecommerceapp.ui.theme.dw

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    onProductClick: (LayoutCoordinates ,Product) -> Unit = { _, _ -> }
) {
    val interactionSource = remember { MutableInteractionSource() }
    var layoutCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }

    Column(
        modifier = modifier
            .width(0.40.dw)
            .heightIn(
                min = 0.24.dh,
                max = 0.27.dh
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                layoutCoordinates?.let {
                    onProductClick(it, product)
                }
            },

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Product Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
//                .height(0.20.dh)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.imageURL)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_product_placeholder),
                contentDescription = "Product Image",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.medium)
                    .onGloballyPositioned { coordinates ->
                        layoutCoordinates = coordinates
                    },
            )
        }

        Spacer(modifier = Modifier.height(0.007.dh))

        // Product Title
        Text(
            text = product.name,
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Center
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(0.006.dh))

        // Product Price
        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.titleMedium.copy(
                textAlign = TextAlign.Center
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}