package ru.lesson.fragmentsample.presentation.detail


sealed class DetailEvent {

    class SaveUserTitle(val text: String): DetailEvent()

    class SaveUserDescription(val text: String): DetailEvent()

    class SaveItem(val id: Long): DetailEvent()
}
