package com.example.ecommerceapp.features.catalog.presentation.widget

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.features.catalog.domain.models.Category
import com.example.ecommerceapp.ui.theme.LocalSpacing
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun CategoryTab(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    category: Category,
    onClick: (Category) -> Unit
) {
    val spacing = LocalSpacing.current

    val identifier = "[CategoryTab]"
    val touchedDown = remember {
        mutableStateOf(false)
    }

    var backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        Color.Transparent
    }

    var contentColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onBackground
    }

    val iconColor = if (isSelected){
MaterialTheme.colorScheme.onPrimary
    } else{ MaterialTheme.colorScheme.primary}

    if (touchedDown.value) {
        backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        contentColor = MaterialTheme.colorScheme.primary
    }

    Card(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        Log.i(
                            identifier,
                            "onPress"
                        )
                        touchedDown.value = true
                        // start
                        val released = try {
                            tryAwaitRelease()
                        } catch (c: CancellationException) {
                            false
                        }
                        if (released) {
                            // ACTION_UP
                            Log.i(
                                identifier,
                                "ACTION_UP"
                            )
                            touchedDown.value = false
                            onClick(category)
                        } else {
                            // ACTION_CANCEL
                            Log.i(
                                identifier,
                                "ACTION_CANCEL"
                            )
                            touchedDown.value = false
                        }
                    },
                )
            },

        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_chair),
                contentDescription = category.name,
                tint = iconColor,
                modifier = Modifier
                    .padding(spacing.medium)
                    .size(16.dp)
            )

            Text(
                text = category.name,
                color = contentColor,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .defaultMinSize()
                    .weight(2f)
                    .padding(end = spacing.tiny)
            )

            Icon(
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = "Arrow Forward",
                tint = contentColor,
                modifier = Modifier
                    .padding(end = spacing.medium)
                    .size(8.dp)
            )

        }
    }
}