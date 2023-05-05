package ru.lesson.fragmentsample.data.repository

import ru.lesson.fragmentsample.data.db.ExampleDao
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity
import ru.lesson.fragmentsample.util.Resource


class ItemRepositoryImpl(private val exampleDao: ExampleDao): ItemRepository {

    override suspend fun getItems(): Resource<List<ExampleEntity>> {

        val response = try {
            exampleDao.getAllExamples()
        } catch(e: Exception) {
            return Resource.Error("Херня, Витя, загружай по новой.")
        }
        return Resource.Success(response)

//        return listOf(
//            ExampleEntity(id = 0, name = "Первый", description = "Описание первого"),
//            ExampleEntity(id = 1, name = "Второй", description = "Описание второго"),
//            ExampleEntity(id = 2, name = "Третий", description = "Описание третьего"),
//            ExampleEntity(id = 3, name = "Четвертый", description = "Описание четвертого"),
//        )
    }

    override suspend fun insertExample(example: ExampleEntity): Resource<Unit> {

        val response = try {
            exampleDao.insertExample(example)
        } catch(e: Exception) {
            return Resource.Error("Херня, Витя, добавляй по новой.")
        }

        return Resource.Success(response)
    }

    override suspend fun deleteExample(id: Long): Resource<Unit> {

        val response = try {
            exampleDao.deleteExample(id)
        } catch(e: Exception) {
            return Resource.Error("Херня, Витя, удаляй по новой.")
        }

        return Resource.Success(response)
    }

}
