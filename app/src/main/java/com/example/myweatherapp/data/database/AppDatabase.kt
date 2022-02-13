package com.example.myweatherapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myweatherapp.data.database.dbmodels.CurrentWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.DailyWeatherDbModel
import com.example.myweatherapp.data.database.converters.WeatherConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(
    entities = [DailyWeatherDbModel::class, CurrentWeatherDbModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(WeatherConverter::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "WeatherDatabase.db"
        private val LOCK = Any()
        private var database: AppDatabase? = null

    }

    abstract fun dao(): WeatherDao

}