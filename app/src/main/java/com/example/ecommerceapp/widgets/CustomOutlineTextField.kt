package com.example.ecommerceapp.widgets

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomOutlineTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String? = null,
    isPasswordVisible: Boolean = false,
    isProtected: Boolean = false,
    placeHolder: String = "Placeholder",
    onPasswordToggleClick: () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = value, selection = TextRange(value.length)))
    }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by  interactionSource.collectIsFocusedAsState()

    val inputPhase = when {
        isFocused -> InputPhase.Focused
        textFieldValue.text.isEmpty() -> InputPhase.UnfocusedEmpty
        else -> InputPhase.UnfocusedNotEmpty
    }

    val transition = updateTransition(targetState = inputPhase, label = "PlaceholderOpacityTransition")
    val placeholderOpacity by transition.animateFloat(label = "PlaceholderOpacityTransition") {
        when (it) {
            InputPhase.Focused -> 1f
            InputPhase.UnfocusedEmpty -> 0.5f
            InputPhase.UnfocusedNotEmpty -> 0.5f
        }
    }


    Column(modifier = modifier.fillMaxWidth()){
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onValueChange(it.text)
            },
            label = { Text(text = label)},
            placeholder = {
                Text(text = placeHolder, color = Color.Gray.copy(alpha = placeholderOpacity))
                          },
            interactionSource = interactionSource,
            isError = errorMessage != null,
            visualTransformation = if (!isPasswordVisible) {
                if (isProtected) PasswordVisualTransformation(mask = '*') else VisualTransformation.None
            } else VisualTransformation.None,
            trailingIcon = {
                if (isProtected) {
                    IconButton(
                        onClick = onPasswordToggleClick
                    ) {
                        Icon(
                            imageVector = if (!isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (!isPasswordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            },
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Start
            ),
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

enum class InputPhase {
    Focused,
    UnfocusedEmpty,
    UnfocusedNotEmpty
}