package ru.lesson.fragmentsample.presentation.detail

import ru.lesson.fragmentsample.presentation.model.ExampleModel


sealed class DetailEvent {
    class SetItem(val item: ExampleModel): DetailEvent()
    class SaveItem(val id: Long): DetailEvent()
}
