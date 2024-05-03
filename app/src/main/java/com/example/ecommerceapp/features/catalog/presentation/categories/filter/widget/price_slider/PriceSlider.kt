package com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget.price_slider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.layoutId
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = ValueRange,
    gap: Int = Gap,
    showIndicator: Boolean = false,
    showLabel: Boolean = false,
    enabled: Boolean = true,
    startThumb: @Composable (thumbValue: RangeSliderState) -> Unit = {
        CustomSliderDefaults.Thumb(it.toString())
    },
    endThumb: @Composable (thumbValue: RangeSliderState) -> Unit = {
        CustomSliderDefaults.Thumb(it.toString())
    },
    track: @Composable (sliderPositions: RangeSliderState) -> Unit = { sliderPositions ->
        CustomSliderDefaults.Track(sliderPositions = sliderPositions)
    },
    indicator: @Composable (indicatorValue: Int) -> Unit = { indicatorValue ->
        CustomSliderDefaults.Indicator(indicatorValue = indicatorValue.toString())
    },
    label: @Composable (labelValue: Int) -> Unit = { labelValue ->
        CustomSliderDefaults.Label(labelValue = labelValue.toString())
    }
) {
    val itemCount = (valueRange.endInclusive - valueRange.start).roundToInt()
    val steps = if (gap == 1) 0 else (itemCount / gap - 1)
    val startInteration

    Box(modifier = modifier) {
        Layout(
            measurePolicy = customSliderMeasurePolicy(
                itemCount = itemCount,
                gap = gap,
                value = value,
                startValue = valueRange.start
            ),

            content = {
                if (showLabel) {
                    Label(
                        modifier = Modifier.layoutId(CustomSliderComponents.LABEL),
                        value = value,
                        label = label
                    )
                }

                Box(modifier = Modifier.layoutId(CustomSliderComponents.START_THUMB)) {
                    startThumb(value.start.roundToInt())
                }

                Box(modifier = Modifier.layoutId(CustomSliderComponents.END_THUMB)) {
                    endThumb(value.endInclusive.roundToInt())
                }

                RangeSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .layoutId(CustomSliderComponents.RANGE_SLIDER),
                    value = value,
                    steps = steps,
                    onValueChange = {
                        onValueChange(it)
                    },
                    startThumb = { startThumb(it) },
                    endThumb = { endThumb(it) },
                    track = track,
                    enabled = enabled
                )

                if (showIndicator) {
                    Indicator(
                        modifier = Modifier.layoutId(CustomSliderComponents.INDICATOR),
                        valueRange = valueRange,
                        gap = gap,
                        indicator = indicator
                    )
                }
            }
        )
    }
}

@Composable
fun Indicator(
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float>,
    gap: Int,
    indicator: @Composable (indicatorValue: Int) -> Unit
) {
    for (i in valueRange.start.roundToInt()..valueRange.endInclusive.roundToInt() step gap) {
        Box(
            modifier = modifier
        ) {
            indicator(i)
        }
    }
}

@Composable
fun Label(
    modifier: Modifier = Modifier,
    value: ClosedFloatingPointRange<Float>,
    label: @Composable (labelValue: Int) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        label(value.start.roundToInt())
    }
}


private fun customSliderMeasurePolicy(
    itemCount: Int,
    gap: Int,
    value: Float,
    startValue: Float
) = MeasurePolicy { measurables, constraints ->
    // Measure the thumb component and calculate its radius.
    val startThumbPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.START_THUMB
    }.measure(constraints)
    val endThumbPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.END_THUMB
    }.measure(constraints)
    val thumbRadius = (endThumbPlaceable.width / 2).toFloat()

    val indicatorPlaceables = measurables.filter {
        it.layoutId == CustomSliderComponents.INDICATOR
    }.map { measurable ->
        measurable.measure(constraints)
    }
    val indicatorHeight = indicatorPlaceables.maxByOrNull { it.height }?.height ?: 0

    val sliderPlaceable = measurables.first {
        it.layoutId == CustomSliderComponents.RANGE_SLIDER
    }.measure(constraints)
    val sliderHeight = sliderPlaceable.height

    val labelPlaceable = measurables.find {
        it.layoutId == CustomSliderComponents.LABEL
    }?.measure(constraints)
    val labelHeight = labelPlaceable?.height ?: 0

    // Calculate the total width and height of the custom slider layout
    val width = sliderPlaceable.width
    val height = labelHeight + sliderHeight + indicatorHeight

    // Calculate the available width for the track (excluding thumb radius on both sides).
    val trackWidth = width - (2 * thumbRadius)

    // Calculate the width of each section in the track.
    val sectionWidth = trackWidth / itemCount
    // Calculate the horizontal spacing between indicators.
    val indicatorSpacing = sectionWidth * gap

    // To calculate offset of the label, first we will calculate the progress of the slider
    // by subtracting startValue from the current value.
    // After that we will multiply this progress by the sectionWidth.
    // Add thumb radius to this resulting value.
    val labelOffset = (sectionWidth * (value - startValue)) + thumbRadius

    layout(width = width, height = height) {
        var indicatorOffsetX = thumbRadius
        // Place label at top.
        // We have to subtract the half width of the label from the labelOffset,
        // to place our label at the center.
        labelPlaceable?.placeRelative(
            x = (labelOffset - (labelPlaceable.width / 2)).roundToInt(),
            y = 0
        )

        // Place slider placeable below the label.
        sliderPlaceable.placeRelative(x = 0, y = labelHeight)

        // Place indicators below the slider.
        indicatorPlaceables.forEach { placeable ->
            // We have to subtract the half width of the each indicator from the indicatorOffset,
            // to place our indicators at the center.
            placeable.placeRelative(
                x = (indicatorOffsetX - (placeable.width / 2)).roundToInt(),
                y = labelHeight + sliderHeight
            )
            indicatorOffsetX += indicatorSpacing
        }
    }
}

