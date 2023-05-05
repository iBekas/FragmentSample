package ru.lesson.fragmentsample.data.repository

import ru.lesson.fragmentsample.data.db.ExampleDao
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity
import ru.lesson.fragmentsample.util.Resource


class ItemRepositoryImpl(private val exampleDao: ExampleDao): ItemRepository {

    // suspend - это создание отложенной функции - корутины, т.е. ассинхронная операция
    // Пока для простоты будем пользоваться корутинами.
    // Resource - кастомный класс, чтобы прямо указать, если придет ошибка
    override suspend fun getItems(): Resource<List<ExampleEntity>> {

        //Пытаемся получить данные из БД, если не вышло, ловим ошибку
        val response = try {
            exampleDao.getAllExamples()
        } catch(e: Exception) {
            //Оборачиваем ошибку в Resource
            return Resource.Error("Херня, Витя, загружай по новой.")
        }
        //Оборачиваем успешный результат в Resource
        return Resource.Success(response)

    }

    override suspend fun insertExample(example: ExampleEntity): Resource<Long> {

        val response = try {
            //Вернет id нового элемента
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
