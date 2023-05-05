package ru.lesson.fragmentsample.data.repository

import ru.lesson.fragmentsample.data.db.entity.ExampleEntity
import ru.lesson.fragmentsample.util.Resource


interface ItemRepository {

    suspend fun getItems(): Resource<List<ExampleEntity>>

    suspend fun insertExample(example: ExampleEntity): Resource<Unit>

    suspend fun deleteExample(id: Long): Resource<Unit>
}
