package com.example.ecommerceapp.features.catalog.presentation.categories.category_detail

import android.util.Log
import android.view.MotionEvent
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
import com.example.ecommerceapp.features.catalog.domain.models.SubCategory
import com.example.ecommerceapp.ui.theme.LocalSpacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SubCategoryOptionButton(
    modifier: Modifier = Modifier,
    subCategory: SubCategory,
    isSelected: Boolean,
    onClick: (SubCategory) -> Unit
) {
    val identifier = "[SubCategoryOptionButton]"
    val touchedDown = remember {
        mutableStateOf(false)
    }

    val spacing = LocalSpacing.current

    LaunchedEffect(touchedDown) {
        if (touchedDown.value) {
            Log.i(identifier, "Button pressed.")
        }
    }

    val selectedBackgroundColor = MaterialTheme.colorScheme.primary
    val selectedContentColor = MaterialTheme.colorScheme.onPrimary

    var backgroundColor = if (isSelected) selectedBackgroundColor else MaterialTheme.colorScheme.surfaceVariant
    var contentColor = if (isSelected) selectedContentColor else MaterialTheme.colorScheme.onSurfaceVariant

    if(touchedDown.value) {
        backgroundColor = selectedBackgroundColor.copy(alpha = 0.35f)
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
    }

    Card(
        modifier = modifier
            .pointerInteropFilter { motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        touchedDown.value = true
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        touchedDown.value = false
                        onClick(subCategory)
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
                text = subCategory.name,
                color = contentColor,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.defaultMinSize()
            )
        }
    }
}