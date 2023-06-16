package ru.lesson.fragmentsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity


@Database(entities = [ExampleEntity::class], version = 1, exportSchema = true)
abstract class ExampleDataBase: RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}
