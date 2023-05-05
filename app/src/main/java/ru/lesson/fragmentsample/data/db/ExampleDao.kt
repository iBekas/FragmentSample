package ru.lesson.fragmentsample.data.db

import androidx.room.*
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity


//Все запросы в БД
//@Dao - указывает что это таблица
//@Query, @Insert и т.п. - указание что делаем
//Запросы ты сам уже должен уметь читать
@Dao
interface ExampleDao {

    @Query("SELECT * FROM example_table")
    suspend fun getAllExamples(): List<ExampleEntity>

    //OnConflictStrategy.REPLACE - в случае повторения данных происходит замена на более свежие
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExample(example: ExampleEntity): Long

    @Query("DELETE FROM example_table WHERE id = :id")
    suspend fun deleteExample(id: Long)

}
