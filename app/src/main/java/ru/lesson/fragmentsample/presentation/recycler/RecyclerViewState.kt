package ru.lesson.fragmentsample.presentation.recycler

import ru.lesson.fragmentsample.presentation.model.ExampleModel


data class RecyclerViewState(
    val itemList: List<ExampleModel> = emptyList(),
    val isLoading: Boolean = false
)
