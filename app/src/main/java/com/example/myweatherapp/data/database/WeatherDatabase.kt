package com.example.myweatherapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweatherapp.data.network.pojo.oneCall.WeatherCurrentPojo
import com.example.myweatherapp.data.network.pojo.oneCall.WeatherDailyPojo

@Database(
    entities = [WeatherDailyPojo::class, WeatherCurrentPojo::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "WeatherDatabase.db"
        private val LOCK = Any()
        private var database: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase {
            synchronized(LOCK) {
                database?.let { return it }
                val instance =
                    Room.databaseBuilder(context, WeatherDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration().build()
                database = instance
                return instance
            }
        }
    }

    abstract fun dao(): WeatherDao

}