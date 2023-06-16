package ru.lesson.fragmentsample.data.repository

import io.reactivex.Observable
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity
import ru.lesson.fragmentsample.util.Resource


interface ItemRepository {
    fun getItems(): Observable<Resource<List<ExampleEntity>>>

    fun insertExample(example: ExampleEntity): Observable<Resource<Long>>

    fun deleteExample(id: Long): Observable<Resource<Unit>>
}
