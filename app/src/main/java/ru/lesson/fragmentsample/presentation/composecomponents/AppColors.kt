package ru.lesson.fragmentsample.presentation.composecomponents

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Stable
class AppColors(
    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    secondaryVariant: Color,
    background: Color,
    surface: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
    onError: Color,
    rippleColor: Color,
    notEnabled: Color
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set
    var primaryVariant by mutableStateOf(primaryVariant, structuralEqualityPolicy())
        internal set
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set
    var secondaryVariant by mutableStateOf(secondaryVariant, structuralEqualityPolicy())
        internal set
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set
    var surface by mutableStateOf(surface, structuralEqualityPolicy())
        internal set
    var error by mutableStateOf(error, structuralEqualityPolicy())
        internal set
    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        internal set
    var onSecondary by mutableStateOf(onSecondary, structuralEqualityPolicy())
        internal set
    var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
        internal set
    var onSurface by mutableStateOf(onSurface, structuralEqualityPolicy())
        internal set
    var onError by mutableStateOf(onError, structuralEqualityPolicy())
        internal set
    var rippleColor by mutableStateOf(rippleColor, structuralEqualityPolicy())
        internal set
    var notEnabled by mutableStateOf(notEnabled, structuralEqualityPolicy())
        internal set

}

fun lightColors(
    primary: Color = Color(0xFFE4F0B1),
    primaryVariant: Color = Color(0XFFE8F133),
    secondary: Color = Color(0xFF619F73),
    secondaryVariant: Color = Color(0xFF217EF6),
    background: Color = Color(0xFFFDECAF),
    surface: Color = Color.White,
    error: Color = Color(0xFFf92a13),
    onPrimary: Color = Color.Black,
    onSecondary: Color = Color.White,
    onBackground: Color = Color.Black,
    onSurface: Color = Color(0xFF34383c),
    onError: Color = Color.White,
    notEnabled: Color = Color(0xFFAAA7A7),
    rippleColor: Color = Color(0XFFADADAD)
): AppColors = AppColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = onError,
    rippleColor = rippleColor,
    notEnabled = notEnabled,
)

fun darkColors(
    primary: Color = Color(0xFF217EF6),
    primaryVariant: Color = Color(0XFFE8F133),
    secondary: Color = Color(0xFF619F73),
    secondaryVariant: Color = Color(0xFFFFC107),
    background: Color = Color(0XFFE8F133),
    surface: Color = Color(0xFF121212),
    error: Color = Color(0xFFCF6679),
    onPrimary: Color = Color.Black,
    onSecondary: Color = Color.Black,
    onBackground: Color = Color.White,
    onSurface: Color = Color.White,
    onError: Color = Color.Black,
    notEnabled: Color = Color(0xFFAAA7A7),
    rippleColor: Color = Color(0XFFADADAD),
): AppColors = AppColors(
    primary,
    primaryVariant,
    secondary,
    secondaryVariant,
    background,
    surface,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSurface,
    onError,
    rippleColor,
    notEnabled,
)
