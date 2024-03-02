package com.example.ecommerceapp.features.auth.presentation.landingpage

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun CurvedCutoutView(
    modifier: Modifier = Modifier,
    color: Color
) {
    Canvas(
        modifier = modifier
        ) {
        val width = size.width
        val height = size.height
//        val height = 471.dp.toPx()

        val point1 = Offset(x = 0f, y = 0f)
        val point2 = Offset(0f, height)
        val controlPoint1 = Offset(width * 0.186f, height * 0.845f)
        val point3 = Offset(width * 0.36f, height * 0.828f)
        val controlPoint2 = Offset(width * 0.52f, height * 0.813f)
        val controlPoint3 = Offset(width * 0.711f, height * 0.8754f)
        val point4 = Offset(width * 0.85f, height * 0.658f)
        val controlPoint4 = Offset(width * 0.922f, height * 0.608f)
        val point5 = Offset(width, height * 0.5488f)
        val point6 = Offset(width, 0f)

        // define path
        val path = Path().apply {
            moveTo(point1.x, point1.y)
            lineTo(point2.x, point2.y)

            // curve
            quadraticBezierTo(
                controlPoint1.x,
                controlPoint1.y,
                point3.x,
                point3.y
            )
            quadraticBezierTo(
                controlPoint2.x,
                controlPoint2.y,
                point4.x,
                point4.y
            )
            quadraticBezierTo(
                controlPoint3.x,
                controlPoint3.y,
                point4.x,
                point4.y
            )
            quadraticBezierTo(
                controlPoint4.x,
                controlPoint4.y,
                point5.x,
                point5.y
            )
            lineTo(point6.x, point6.y)
            close()
        }

        drawPath(path = path, color = color)

    }
}