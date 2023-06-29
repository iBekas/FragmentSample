package ru.lesson.fragmentsample.presentation.composecomponents

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import ru.lesson.fragmentsample.app.App
import ru.lesson.fragmentsample.presentation.THEME_CODE


private val LightColorPalette = lightColors()
private val DarkColorPalette = darkColors()

@Composable
fun FragmentSampleTheme(
    content: @Composable () -> Unit
) {
    val themeCode = App.getSettings().getInt(THEME_CODE, 1)

    val colors = if (themeCode == 1) {
        LightColorPalette
    } else {
        DarkColorPalette
    }

    val configuration = LocalConfiguration.current

    val dimensions: AppDimens
    val typography: AppTypography

    when(themeCode) {
        1 -> {
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
