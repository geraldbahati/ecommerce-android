package com.example.ecommerceapp.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StaggeredFlowRow(
    modifier: Modifier = Modifier,
    mainAxisSpacing: Dp = 0.dp,
    crossAxisSpacing: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Keep track of each row's width and height
        val rowWidths = mutableListOf<Int>()
        val rowHeights = mutableListOf<Int>()

        val placeables = measurables.map { measurable ->
            // Measure each child with the max width it can take
            measurable.measure(constraints.copy(minWidth = 0))
        }

        var currentWidth = 0
        var currentHeight = 0
        var maxHeightInRow = 0

        placeables.forEach { placeable ->
            if (currentWidth + placeable.width > constraints.maxWidth) {
                // Move to the next row if adding the current placeable exceeds the max width
                rowWidths.add(currentWidth)
                rowHeights.add(maxHeightInRow)
                currentWidth = 0
                currentHeight += maxHeightInRow + crossAxisSpacing.roundToPx()
                maxHeightInRow = 0
            }
            // Update the current row width and the max height found in the current row
            currentWidth += placeable.width + mainAxisSpacing.roundToPx()
            maxHeightInRow = maxOf(maxHeightInRow, placeable.height)
        }
        // Add the last row
        rowWidths.add(currentWidth)
        rowHeights.add(maxHeightInRow)

        // Calculate the total height of the staggered grid
        val totalHeight = rowHeights.sum() + crossAxisSpacing.roundToPx() * (rowHeights.size - 1)
        val width = constraints.maxWidth

        layout(width, totalHeight) {
            var yPos = 0
            var xPos = 0
            var rowIndex = 0

            placeables.forEachIndexed { index, placeable ->
                if (xPos + placeable.width > width) {
                    // Move to next row
                    xPos = 0
                    yPos += rowHeights[rowIndex] + crossAxisSpacing.roundToPx()
                    rowIndex++
                }
                placeable.placeRelative(x = xPos, y = yPos)
                xPos += placeable.width + mainAxisSpacing.roundToPx()
            }
        }
    }
}