package com.example.ecommerceapp.features.catalog.presentation.categories.search

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.ui.theme.LocalSpacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OptionOutlineButton(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: (Category) -> Unit
) {
    val identifier = "[OptionOutlineButton]"
    val touchedDown = remember {
        mutableStateOf(false)
    }

    val spacing = LocalSpacing.current

    LaunchedEffect(touchedDown) {
        if (touchedDown.value) {
            Log.i(identifier, "Button pressed.")
        }
    }

    var backgroundColor = MaterialTheme.colorScheme.background
    var contentColor = MaterialTheme.colorScheme.secondary
    var outlineColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3016f)


    if (touchedDown.value) {
        backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        contentColor = MaterialTheme.colorScheme.primary
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
                        onClick(category)
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
                .padding(
                    horizontal = spacing.medium,
                    vertical = spacing.small
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category.name,
                color = contentColor,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.defaultMinSize()
            )
        }
    }

}