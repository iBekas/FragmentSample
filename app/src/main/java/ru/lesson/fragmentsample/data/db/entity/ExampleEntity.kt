package ru.lesson.fragmentsample.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

//Название таблицы
private const val TABLE_NAME = "example_table"

//Аннотация указывающая, что это сущность для бд
@Entity(tableName = TABLE_NAME)
data class ExampleEntity(
    //Ключ, по которому происходит добавление и замены, autoGenerate = true
    // - указывает на то, что id будет повышаться автоматически.
    //Если 0 - значит данных в таблице еще нет, будет созданна новая сущность
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String
)
