package ru.lesson.fragmentsample.presentation.composecomponents

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val shapes: AppShape
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current

    val dimens: AppDimens
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimens.current
}

val LocalAppColors = staticCompositionLocalOf<AppColors> {
    error("No colors provided")
}

val LocalAppTypography = staticCompositionLocalOf<AppTypography> {
    error("No typography provided")
}

val LocalAppShapes = staticCompositionLocalOf {
    AppShape()
}

val LocalAppDimens = staticCompositionLocalOf<AppDimens> {
    error("No dimensions provided")
}

@Immutable
internal object WrsRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = AppTheme.colors.rippleColor

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 1f,
        focusedAlpha = 1f,
        hoveredAlpha = 1f,
        pressedAlpha = 1f,
    )
}
