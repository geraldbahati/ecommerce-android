package com.example.ecommerceapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = GreenGrey80,
    onPrimary = LightBlue20,
    primaryContainer = LightBlue30,
    onPrimaryContainer = LightBlue96,
    inversePrimary = LightBlue80,

    secondary = DarkGreen80,
    onSecondary = DarkGreen20,
    secondaryContainer = DarkGreen30,
    onSecondaryContainer = DarkGreen90,


    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,

    background = Grey10,
    onBackground = Grey90,

    surface = GreenGrey30,
    onSurface = GreenGrey80,

    inverseSurface = Grey90,
    inverseOnSurface = Grey10,

    surfaceVariant = GreenGrey30,
    onSurfaceVariant = GreenGrey80,

    outline = GreenGrey80
)


private val LightColorScheme = lightColorScheme(
    primary = Grey40,
    onPrimary = Grey10,
    primaryContainer = Grey90,
    onPrimaryContainer = Grey10,
    inversePrimary = Grey99,

    secondary = Grey10,
    onSecondary = Grey95,
    secondaryContainer = Grey90,
    onSecondaryContainer = Grey10,


    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,

    background = LightBlue96,
    onBackground = Grey10,

    surface = GreenGrey90,
    onSurface = Grey10,

    inverseSurface = Grey20,
    inverseOnSurface = Grey30,

    surfaceVariant = Grey30,
    onSurfaceVariant = Grey30,

    outline = Grey80,
)

@Composable
fun EcommerceAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(
            tiny = tinySpacing,
            small = smallSpacing,
            medium = mediumSpacing,
            large = largeSpacing,
            huge = hugeSpacing,
            extraHuge = extraHugeSpacing,
            extraExtraHuge = extraExtraHugeSpacing
        ),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = shapes,
            content = content
        )
    }
}