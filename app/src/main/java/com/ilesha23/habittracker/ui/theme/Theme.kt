package com.ilesha23.habittracker.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    primaryContainer = LightGreen,
    onPrimaryContainer = Black,
    onPrimary = DarkGray,
    inversePrimary = DarkGray,

    secondary = LightBlue,
    secondaryContainer = LightRed,
    onSecondaryContainer = Black,

    tertiaryContainer = LightGray,
    onTertiaryContainer = Black,
    tertiary = Gray,
    onTertiary = HalfTransparentBlue,

    background = White
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    primaryContainer = LightGreen,
    onPrimaryContainer = Black,
    onPrimary = DarkGray,
    inversePrimary = DarkGray,

    secondary = LightBlue,
    secondaryContainer = LightRed,
    onSecondaryContainer = Black,

    tertiaryContainer = LightGray,
    onTertiaryContainer = Black,
    tertiary = Gray,
    onTertiary = HalfTransparentBlue,

    background = White
)

@Composable
fun HabitTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = Typography, content = content
    )
}