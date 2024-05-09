package com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PriceRangeSlider(
    minPrice: Float,
    maxPrice: Float,
    initialMinPrice: Float = minPrice,
    initialMaxPrice: Float = maxPrice,
    onValueChangeFinished: (ClosedFloatingPointRange<Float>) -> Unit
) {
    var priceRange by remember { mutableStateOf(initialMinPrice..initialMaxPrice) }

    Column(
        modifier = Modifier.padding(0.dp),
    ) {

        // title
        Text(
            text = "Price",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 15.sp,
                lineHeight = 20.sp
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )


        RangeSliderWithLabel(
            value = priceRange,
            onValueChange = { newRange -> priceRange = newRange },
            valueRange = minPrice..maxPrice,
            onValueChangeFinished = onValueChangeFinished
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RangeSliderWithLabel(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChangeFinished: (ClosedFloatingPointRange<Float>) -> Unit,
    labelWidth: Dp = 32.dp
) {

        RangeSlider(
            value = value,
            onValueChange = onValueChange,
            valueRange = valueRange,
            onValueChangeFinished = { onValueChangeFinished(value) },
            colors = SliderDefaults.colors(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            steps = 0,
            track = {
                    rangeSliderState ->
                    SliderDefaults.Track(
                        rangeSliderState = rangeSliderState,
                        modifier = Modifier
                            .scale(scaleX = 1f, scaleY = 1.5f)
                    )
            },
            endThumb = {},
            startThumb = {}
        )

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-16).dp)
        ) {
            val density = LocalDensity.current
            val boxWidthPx = constraints.maxWidth
            val labelWidthPx = with(density) { labelWidth.toPx() }

            val leftOffset = remember(value.start, valueRange, boxWidthPx, labelWidthPx, density) {
                derivedStateOf {
                    getSliderOffset(
                        value.start,
                        valueRange,
                        boxWidthPx,
                        labelWidthPx,
                        density
                    )
                }
            }.value

            val rightOffset =
                remember(value.endInclusive, valueRange, boxWidthPx, labelWidthPx, density) {
                    derivedStateOf {
                        getSliderOffset(
                            value.endInclusive,
                            valueRange,
                            boxWidthPx,
                            labelWidthPx,
                            density
                        )
                    }
                }.value

            SliderLabel(
                text = "$${value.start.toInt()}",
                minWidth = labelWidth,
                offset = leftOffset
            )
            SliderLabel(
                text = "$${value.endInclusive.toInt()}",
                minWidth = labelWidth,
                offset = rightOffset
            )
        }
}

@Composable
fun SliderLabel(
    text: String,
    minWidth: Dp,
    offset: Dp
) {
    Box(
        modifier = Modifier
            .padding(start = offset)
            .width(minWidth),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 12.sp)
    }
}

fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Int,
    labelWidth: Float,
    density: Density
): Dp {
    val normalizedValue = (value - valueRange.start) / (valueRange.endInclusive - valueRange.start)
    val pixelOffset = normalizedValue * (boxWidth - labelWidth)
    return with(density) { pixelOffset.coerceIn(0f, (boxWidth - labelWidth)).toDp() }
}

@Preview
@Composable
fun PriceRangeSliderPreview() {
    PriceRangeSlider(
        minPrice = 0f,
        maxPrice = 100f,
        initialMinPrice = 40f,
        initialMaxPrice = 80f,
        onValueChangeFinished = {}
    )
}
