package ru.lesson.fragmentsample.data.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity


@Dao
interface ExampleDao {

    //Есть пять типов возвращаемых значений Observable, Single, Flowable, Maybe, Completable
    //Что они делаеют и зачем нужны на самостоятельно обучение
    //В примере используются разные типа для примера, подобрать корректный в твоей ситуации - твоя задача

    @Query("SELECT * FROM example_table")
    fun getAllExamples(): Observable<List<ExampleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExample(example: ExampleEntity): Single<Long>

    @Query("DELETE FROM example_table WHERE id = :id")
    fun deleteExample(id: Long): Completable

}
