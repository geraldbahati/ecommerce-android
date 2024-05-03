package com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget

import android.graphics.Color.parseColor
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColourDotOption(
    modifier: Modifier = Modifier,
    colour: String,
    isSelected: Boolean,
    onColourSelected: (String) -> Unit,
    activeColor: Color = Color.White,
) {

    val circleSize = 20.dp
    val innerCircle = 14.dp

    Canvas(modifier = modifier
        .size(circleSize)
        .clickable(
            onClick = { onColourSelected(colour) }
        )
    ) {
        val innerRadius = innerCircle.toPx() / 2
        val radius = circleSize.toPx() / 2

        drawCircle(
            color = Color(parseColor(colour)),
            radius = radius
        )

        if (isSelected) {
            drawCircle(
                color = activeColor,
                radius = innerRadius
            )
        }
    }
}