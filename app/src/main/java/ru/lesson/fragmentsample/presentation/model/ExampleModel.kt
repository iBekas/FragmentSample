package ru.lesson.fragmentsample.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExampleModel(
    val id: Long,
    var name: String,
    var description: String
): Parcelable
