package ru.lesson.fragmentsample.data.repository

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import ru.lesson.fragmentsample.data.db.ExampleDao
import ru.lesson.fragmentsample.data.db.entity.ExampleEntity
import ru.lesson.fragmentsample.util.Resource


class ItemRepositoryImpl(private val exampleDao: ExampleDao) : ItemRepository {
    override fun getItems(): Observable<Resource<List<ExampleEntity>>> {
        return exampleDao.getAllExamples()
            //Обарачиваем результат в Resource
            .map<Resource<List<ExampleEntity>>> { Resource.Data(it) }
            //В случае ошибки, так же обрачиваем в Resource
            .onErrorReturn { Resource.Error(it) }
            //Начинаем цепочку с Loading, чтобы отобразить загрузку
            .startWith(Resource.Loading)
            //Прямо указывает, что данная операчия будет проходить в другом потоке
            .subscribeOn(Schedulers.io())
    }

    override fun insertExample(example: ExampleEntity): Observable<Resource<Long>> {
        return exampleDao.insertExample(example)
            //Меняем тип на Observable, чтобы использовать ниже startWith
            .toObservable()
            .map<Resource<Long>> { Resource.Data(it) }
            .onErrorReturn { Resource.Error(it) }
            .startWith(Resource.Loading)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteExample(id: Long): Observable<Resource<Unit>> {
        return exampleDao.deleteExample(id)
            //Как только операция Completable завершится успехом, мы меняем её тип на Observable,
            // и кладем в него Resource.Success
            .andThen(Observable.just(Resource.Success))
            .onErrorReturn { Resource.Error(it) }
            .startWith(Resource.Loading)
            .subscribeOn(Schedulers.io())
    }

}
