package ru.lesson.fragmentsample.presentation.composecomponents

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
data class AppShape(
    val small: RoundedCornerShape = RoundedCornerShape(4.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(8.dp),
    val large: RoundedCornerShape = RoundedCornerShape(12.dp),
    val xlarge: RoundedCornerShape = RoundedCornerShape(16.dp),
)
