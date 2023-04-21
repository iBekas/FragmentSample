package ru.lesson.fragmentsample.data.repository

import ru.lesson.fragmentsample.data.model.ExampleModel


interface ItemRepository {

    fun getItems(): List<ExampleModel>
}
