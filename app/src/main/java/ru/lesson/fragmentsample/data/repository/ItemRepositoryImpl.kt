package ru.lesson.fragmentsample.data.repository

import ru.lesson.fragmentsample.data.model.ExampleModel


class ItemRepositoryImpl: ItemRepository {

    override fun getItems(): List<ExampleModel> {
        return listOf(
            ExampleModel(id = 0, name = "Первый", description = "Описание первого"),
            ExampleModel(id = 1, name = "Второй", description = "Описание второго"),
            ExampleModel(id = 2, name = "Третий", description = "Описание третьего"),
            ExampleModel(id = 3, name = "Четвертый", description = "Описание четвертого"),
        )
    }

}
