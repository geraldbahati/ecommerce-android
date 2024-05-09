package com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget

import android.view.MotionEvent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MoreButton(
    modifier: Modifier = Modifier,
    onItemSelected: () -> Unit
) {
    val (touchedDown, setTouchedDown) = remember { mutableStateOf(false) }

    val backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = if (touchedDown) 1f else 0.297f)
    val contentColor = if (touchedDown) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
    val outlineColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3016f)

    Card(
        modifier = modifier
            .clickable(
                onClick = onItemSelected,
                onClickLabel = "Show more options"
            )
            .pointerInteropFilter { motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        setTouchedDown(true)
                        true
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        setTouchedDown(false)
                        true
                    }
                    else -> false
                }
            },
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, outlineColor),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "+ More",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.sp, lineHeight = 16.sp),
                textAlign = TextAlign.Center
            )
        }
    }
}
