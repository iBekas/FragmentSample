package ru.lesson.fragmentsample.app

import android.app.Application
import androidx.room.Room
import androidx.room.RoomMasterTable.TABLE_NAME
import ru.lesson.fragmentsample.data.db.ExampleDao
import ru.lesson.fragmentsample.data.db.ExampleDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object{

        private var appInstance : App? = null
        private var db: ExampleDataBase? = null

        fun getExampleDao(): ExampleDao {
            checkDb()
            return db!!.exampleDao()
        }

        private fun checkDb() {
            if (db == null) {
                val builder = Room.databaseBuilder(
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
