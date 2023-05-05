package ru.lesson.fragmentsample.presentation.recycler

import ru.lesson.fragmentsample.presentation.model.ExampleModel


data class RecyclerViewState(
    val itemList: List<ExampleModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorText: String = ""
) {

    //Чтобы не плодить код во вью, делаем пустую заметку в состоянии
    fun getEmptyItem(): ExampleModel {
        return ExampleModel(
            // если id равен 0, room db поймет, что такого элемента еще нет и автоматически присудит id
            id = 0,
            name = "",
            description = ""
        )
    }

}
