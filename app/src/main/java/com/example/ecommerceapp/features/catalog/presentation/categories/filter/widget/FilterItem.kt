package com.example.ecommerceapp.features.catalog.presentation.categories.filter.widget

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun FilterItem(
    modifier: Modifier = Modifier,
    item: String,
    isSelected: Boolean,
    onItemSelected: (String) -> Unit
) {
    val identifier = "[FilterItem]"
    val touchedDown = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(touchedDown) {
        if (touchedDown.value) {
            Log.i(identifier, "Button pressed.")
        }
    }

    var backgroundColor = MaterialTheme.colorScheme.background
    var contentColor = MaterialTheme.colorScheme.onBackground
    var outlineColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3016f)

//    if ()

    if (isSelected) {
        backgroundColor = MaterialTheme.colorScheme.primary
        contentColor = MaterialTheme.colorScheme.onPrimary
        outlineColor = MaterialTheme.colorScheme.primary
    }

    Card (
        modifier = modifier
            .pointerInteropFilter { motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        touchedDown.value = true
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        touchedDown.value = false
                        onItemSelected(item)
                        true
                    }
                    MotionEvent.ACTION_CANCEL -> {
                        touchedDown.value = false
                        true
                    }
                    else -> false
                }
            },

        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, outlineColor),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = contentColor,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = item,
                color = contentColor,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 13.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}