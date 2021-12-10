package com.example.myweatherapp.data.database.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myweatherapp.data.network.pojo.oneCall.Temp

@Entity(tableName = "weather_daily")
data class DailyWeatherDbModel(
    @PrimaryKey
    val dt: Long,
    val temp: TempDb? = null,
    val pressure: Int? = null,
    val humidity: Int? = null,
    val precipitation: Int? = null,
    val windSpeed: Float? = null,
    val windDeg: Int? = null,
    val weather: WeatherDetailsDb? = null,
    val clouds: Int? = null,
)