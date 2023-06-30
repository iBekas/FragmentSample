package ru.lesson.fragmentsample.presentation.composecomponents

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

const val THEME_CODE = "THEME_CODE"
const val FIRST_THEME = 0
const val SECOND_THEME = 1

private val LightColorPalette = lightColors()
private val DarkColorPalette = darkColors()

@Composable
fun FragmentSampleTheme(
    themeCode: Int = 0,
    content: @Composable () -> Unit
) {

    val colors = if (themeCode == FIRST_THEME) {
        LightColorPalette
    } else {
        DarkColorPalette
    }

    val dimensions: AppDimens
    val typography: AppTypography

    when(themeCode) {
        FIRST_THEME -> {
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
