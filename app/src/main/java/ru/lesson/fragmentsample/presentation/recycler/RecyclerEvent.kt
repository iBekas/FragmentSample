package ru.lesson.fragmentsample.presentation.recycler

sealed class RecyclerEvent {
    object GetItems : RecyclerEvent()
    class ShowDeleteDialog(val isShow: Boolean, val id: Long) : RecyclerEvent()
    class ShowSettingsDialog(val isShow: Boolean) : RecyclerEvent()
    class DeleteItem(val id: Long) : RecyclerEvent()
    class SetTheme(val themeCode: Int) : RecyclerEvent()
}
