package com.example.myweatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myweatherapp.data.database.converters.WeatherConverter
import com.example.myweatherapp.data.database.dbmodels.CurrentWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.DailyWeatherDbModel

@Database(
    entities = [DailyWeatherDbModel::class, CurrentWeatherDbModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(WeatherConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): WeatherDao

}