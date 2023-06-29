package ru.lesson.fragmentsample.presentation.composecomponents

import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Immutable
class AppTypography(
    title0: TextStyle,
    h2: TextStyle,
    h3: TextStyle,
    h4: TextStyle,
    h5: TextStyle,
    h6: TextStyle,
    subtitle1: TextStyle,
    subtitle2: TextStyle,
    body1: TextStyle,
    body2: TextStyle,
    button: TextStyle,
    caption: TextStyle,
    overline: TextStyle,
) {
    var title0 by mutableStateOf(title0, structuralEqualityPolicy())
        internal set
    var h2 by mutableStateOf(h2, structuralEqualityPolicy())
        internal set
    var h3 by mutableStateOf(h3, structuralEqualityPolicy())
        internal set
    var h4 by mutableStateOf(h4, structuralEqualityPolicy())
        internal set
    var h5 by mutableStateOf(h5, structuralEqualityPolicy())
        internal set
    var h6 by mutableStateOf(h6, structuralEqualityPolicy())
        internal set
    var subtitle1 by mutableStateOf(subtitle1, structuralEqualityPolicy())
        internal set
    var subtitle2 by mutableStateOf(subtitle2, structuralEqualityPolicy())
        internal set
    var body1 by mutableStateOf(body1, structuralEqualityPolicy())
        internal set
    var body2 by mutableStateOf(body2, structuralEqualityPolicy())
        internal set
    var button by mutableStateOf(button, structuralEqualityPolicy())
        internal set
    var caption by mutableStateOf(caption, structuralEqualityPolicy())
        internal set
    var overline by mutableStateOf(overline, structuralEqualityPolicy())
        internal set
}

fun normalTypography(
    title0: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        fontFamily = FontFamily.Default,
        lineHeight = 41.sp,
        letterSpacing = 0.37.sp,
    ),
    h2: TextStyle = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp,
        fontFamily = FontFamily.Default
    ),
    h3: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily.Default
    ),
    h4: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp,
        fontFamily = FontFamily.Default
    ),
    h5: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily.Default
    ),
    h6: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp,
        fontFamily = FontFamily.Monospace
    ),
    subtitle1: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp,
        fontFamily = FontFamily.Serif
    ),
    subtitle2: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp,
        fontFamily = FontFamily.Serif
    ),
    body1: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontFamily.Default
    ),
    body2: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
        fontFamily = FontFamily.Default
    ),
    button: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
        fontFamily = FontFamily.Serif
    ),
    caption: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp,
        fontFamily = FontFamily.Default
    ),
    overline: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Default
    ),
): AppTypography = AppTypography(
    title0 = title0,
    h2 = h2,
    h3 = h3,
    h4 = h4,
    h5 = h5,
    h6 = h6,
    subtitle1 = subtitle1,
    subtitle2 = subtitle2,
    body1 = body1,
    body2 = body2,
    button = button,
    caption = caption,
    overline = overline,
)

fun cursiveTypography(
    title0: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        fontFamily = FontFamily.Cursive,
        lineHeight = 41.sp,
        letterSpacing = 0.37.sp,
    ),
    h2: TextStyle = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp,
        fontFamily = FontFamily.Cursive
    ),
    h3: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily.Cursive
    ),
    h4: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp,
        fontFamily = FontFamily.Cursive
    ),
    h5: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.sp,
        fontFamily = FontFamily.Cursive
    ),
    h6: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp,
        fontFamily = FontFamily.Monospace
    ),
    subtitle1: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        letterSpacing = 0.15.sp,
        fontFamily = FontFamily.Serif
    ),
    subtitle2: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp,
        fontFamily = FontFamily.Serif
    ),
    body1: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        letterSpacing = 0.5.sp,
        fontFamily = FontFamily.Cursive
    ),
    body2: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
        fontFamily = FontFamily.Cursive
    ),
    button: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
        fontFamily = FontFamily.Serif
    ),
    caption: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.4.sp,
        fontFamily = FontFamily.Cursive
    ),
    overline: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp,
        fontFamily = FontFamily.Cursive
    ),
): AppTypography = AppTypography(
    title0 = title0,
    h2 = h2,
    h3 = h3,
    h4 = h4,
    h5 = h5,
    h6 = h6,
    subtitle1 = subtitle1,
    subtitle2 = subtitle2,
    body1 = body1,
    body2 = body2,
    button = button,
    caption = caption,
    overline = overline,
)
