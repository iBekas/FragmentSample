package ru.lesson.fragmentsample.presentation.recycler

sealed class RecyclerEvent {
    object GetItems : RecyclerEvent()

    class DeleteItem(val id: Long) : RecyclerEvent()
}
