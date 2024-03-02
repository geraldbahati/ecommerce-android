package com.example.ecommerceapp.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

data class Spacing(
    val tiny: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val huge: Dp,
    val extraHuge: Dp,
    val extraExtraHuge: Dp
)

val LocalSpacing = staticCompositionLocalOf<Spacing> {
    error("No Spacing provided")
}