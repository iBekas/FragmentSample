package ru.lesson.fragmentsample.data.db

import androidx.room.*
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity

@Dao
interface ExampleDao {

    @Query("SELECT * FROM example_table")
    suspend fun getAllExamples(): List<ExampleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExample(example: ExampleEntity)

    @Query("DELETE FROM example_table WHERE id = :id")
    suspend fun deleteExample(id: Long)

}
