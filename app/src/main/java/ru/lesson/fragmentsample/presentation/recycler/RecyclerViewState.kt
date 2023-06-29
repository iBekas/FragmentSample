package ru.lesson.fragmentsample.presentation.recycler

import ru.lesson.fragmentsample.presentation.model.ExampleModel


data class RecyclerViewState(
    val itemList: List<ExampleModel> = emptyList(),
    val deletableItemId: Long = -1L,
    val currentTheme: Int = 0,
    val isShowDeleteDialog: Boolean = false,
    val isShowSettingsDialog: Boolean = false,
    val isLoading: Boolean = false,
    val errorText: String = ""
) {

    fun getEmptyItem(): ExampleModel {
        return ExampleModel(
            id = 0,
            name = "",
            description = ""
        )
    }

}
