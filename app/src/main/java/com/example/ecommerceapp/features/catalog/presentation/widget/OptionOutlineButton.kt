package com.example.ecommerceapp.features.catalog.presentation.widget

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.ui.theme.LocalSpacing
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun OptionOutlineButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    val identifier = "[OptionOutlineButton]"
    val touchedDown = remember {
        mutableStateOf(false)
    }

    val spacing = LocalSpacing.current

    var backgroundColor = MaterialTheme.colorScheme.background
    var contentColor = MaterialTheme.colorScheme.secondary
    var outlineColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3016f)

    val selectedBackgroundColor = MaterialTheme.colorScheme.primary
    val selectedContentColor = MaterialTheme.colorScheme.onPrimary

    if (touchedDown.value) {
        backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        contentColor = MaterialTheme.colorScheme.primary
        outlineColor = MaterialTheme.colorScheme.primary
    }

    Card (
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
                            onClick()
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
                text = title,
                color = contentColor,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.defaultMinSize()
            )
        }
    }

}