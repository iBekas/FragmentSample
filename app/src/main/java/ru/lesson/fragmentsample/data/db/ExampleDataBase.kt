package ru.lesson.fragmentsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity


//Сама база, указываем сущности, версию(в случае изменения сущностей, необходимо поднять и прописать миграцию),
//exportSchema - автоматическое создание и изменение схемы базы данных
@Database(entities = [ExampleEntity::class], version = 1, exportSchema = true)
abstract class ExampleDataBase: RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}
