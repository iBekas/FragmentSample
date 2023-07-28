package ru.lesson.fragmentsample.app

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomMasterTable.TABLE_NAME
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.lesson.fragmentsample.data.db.ExampleDao
import ru.lesson.fragmentsample.data.db.ExampleDataBase
import ru.lesson.fragmentsample.data.network.PokeApi

private const val BASE_URL = "https://pokeapi.co/api/v2/"

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var db: ExampleDataBase? = null
        private var sharedPreferences: SharedPreferences? = null
        private var api: PokeApi? = null

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

        fun getSettings(): SharedPreferences {
            if (sharedPreferences == null) {
                sharedPreferences =
                    appInstance!!.applicationContext.getSharedPreferences("THEME", MODE_PRIVATE)
            }
            return sharedPreferences!!
        }

        fun getPokemonApi(): PokeApi {
            return api
                ?: Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(PokeApi::class.java)
        }
    }
}
