package ru.lesson.fragmentsample.presentation.detail


sealed class DetailEvent {

    class SaveUserText(val text: String): DetailEvent()
}
