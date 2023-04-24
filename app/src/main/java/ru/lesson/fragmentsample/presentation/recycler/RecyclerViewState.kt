package ru.lesson.fragmentsample.presentation.recycler

import ru.lesson.fragmentsample.presentation.model.ExampleModel


//По сути обычный класс данных, используется для удобства, если в liveData больше чем одно поле
data class RecyclerViewState(
    val itemList: List<ExampleModel> = emptyList(),
    val isLoading: Boolean = false
)
