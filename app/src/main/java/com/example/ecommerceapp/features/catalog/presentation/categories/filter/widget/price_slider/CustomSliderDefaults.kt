package com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget.price_slider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.SliderPositions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object CustomSliderDefaults {

    @Composable
    fun Thumb(
        thumbValue: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.primary,
        size: Dp = ThumbSize,
        shape: Shape = CircleShape,
        content: @Composable () -> Unit = {
            Text(
                text = thumbValue,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        },
    ) {
        Box(
            modifier = modifier
                .size(size)
                .clip(shape)
                .background(color)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Track(
        sliderPositions: RangeSliderState,
        modifier: Modifier = Modifier,
        trackColor: Color = TrackColor,
        progressColor: Color = PrimaryColor,
        height: Dp = TrackHeight,
        shape: Shape = CircleShape
    ) {
        Box(
            modifier = modifier
                .track(height = height, shape = shape)
                .background(trackColor)
        ) {
            Box(
                modifier = Modifier
                    .progress(
                        sliderPositions = sliderPositions,
                        height = height,
                        shape = shape
                    )
                    .background(progressColor)
            )
        }
    }

    @Composable
    fun Indicator(
        indicatorValue: String,
        modifier: Modifier = Modifier,
        style: TextStyle = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal)
    ) {
        Box(modifier = modifier) {
            Text(
                text = indicatorValue,
                style = style,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun Label(
        labelValue: String,
        modifier: Modifier = Modifier,
        style: TextStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)
    ) {
        Box(modifier = modifier) {
            Text(
                text = labelValue,
                style = style,
                textAlign = TextAlign.Center
            )
        }
    }
}