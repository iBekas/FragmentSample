package ru.lesson.fragmentsample.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExampleModel(
    val id: Long,
    val name: String,
    val description: String
): Parcelable
