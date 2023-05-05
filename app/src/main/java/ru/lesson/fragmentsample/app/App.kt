package ru.lesson.fragmentsample.app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomMasterTable.TABLE_NAME
import ru.lesson.fragmentsample.data.db.ExampleDao
import ru.lesson.fragmentsample.data.db.ExampleDataBase

class App : Application() {

    //Application создается в одном экземпляре
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object{

        private var appInstance : App? = null
        private var db: ExampleDataBase? = null

        //Получем экземпляр базы
        fun getExampleDao(): ExampleDao {
            checkDb()
            return db!!.exampleDao()
        }

        private fun checkDb() {
            //Проверяем существует ли уже база, если нет создаем в единственном на всё приложение экземпляре
            if (db == null) {
                val builder = Room.databaseBuilder(
                    //Контекст самомго приложения
                    appInstance!!.applicationContext,
                    ExampleDataBase::class.java,
                    TABLE_NAME
                )
                db = builder
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}
