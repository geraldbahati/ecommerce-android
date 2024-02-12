package com.example.ecommerceapp.widgets

import android.content.res.Resources.Theme
import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
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
import kotlin.coroutines.cancellation.CancellationException

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit
) {
    val identifier = "[CustomButton]"
    val touchedDown = remember {
        mutableStateOf(false)
    }

    var backgroundColor = MaterialTheme.colorScheme.secondary
    var contentColor = MaterialTheme.colorScheme.onSecondary

    if (touchedDown.value) {
        backgroundColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
        contentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f)
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
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
    ){
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = contentColor,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                ),
                modifier = Modifier.defaultMinSize()
            )
        }

    }
}