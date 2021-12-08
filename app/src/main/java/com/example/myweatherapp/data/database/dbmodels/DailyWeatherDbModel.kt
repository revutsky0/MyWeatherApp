package com.example.myweatherapp.data.database.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myweatherapp.data.network.pojo.oneCall.Temp

@Entity(tableName = "weather_daily")
data class DailyWeatherDbModel(
    @PrimaryKey
    val dt: Long,
    val temp: Temp? = null,
    val pressure: Int? = null,
    val humidity: Int? = null,
    val windSpeed: Float? = null,
    val windDeg: Int? = null,
    val weather: List<WeatherDetailsDbModel>? = null,
    val clouds: Int? = null,
)