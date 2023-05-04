package ru.lesson.fragmentsample.presentation.recycler

import ru.lesson.fragmentsample.presentation.model.ExampleModel

sealed class RecyclerEvent {
    object GetItems : RecyclerEvent()

    class AddItem(val item: ExampleModel) : RecyclerEvent()
}
