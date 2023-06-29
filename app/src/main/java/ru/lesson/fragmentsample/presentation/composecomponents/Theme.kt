package ru.lesson.fragmentsample.presentation.composecomponents

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration


private val LightColorPalette = lightColors()
private val DarkColorPalette = darkColors()

@Composable
fun FragmentSampleTheme(
    themeCode: Int = 0,
    content: @Composable () -> Unit
) {

    val colors = if (themeCode == 0) {
        LightColorPalette
    } else {
        DarkColorPalette
    }

    val configuration = LocalConfiguration.current

    val dimensions: AppDimens
    val typography: AppTypography

    when(themeCode) {
        0 -> {
            dimensions = normalDimensions()
            typography = normalTypography()
        }
        else -> {
            dimensions = smallDimensions()
            typography = cursiveTypography()
        }
    }

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        LocalAppShapes provides AppTheme.shapes,
        LocalRippleTheme provides WrsRippleTheme,
        LocalAppDimens provides dimensions,
        content = content,
    )

}
