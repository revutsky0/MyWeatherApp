package com.example.myweatherapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myweatherapp.data.database.dbmodels.CurrentWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.DailyWeatherDbModel
import com.example.myweatherapp.data.database.converters.WeatherConverter

@Database(
    entities = [DailyWeatherDbModel::class, CurrentWeatherDbModel::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(WeatherConverter::class)
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