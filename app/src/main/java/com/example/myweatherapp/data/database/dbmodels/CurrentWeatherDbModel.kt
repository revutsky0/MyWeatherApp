package com.example.myweatherapp.data.database.dbmodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_current")
data class CurrentWeatherDbModel(

    @PrimaryKey
    val dt: Long,
    val temp: Float? = null,
    val feelsLike: Float? = null,
    val pressure: Int? = null,
    val humidity: Int? = null,
    val clouds: Int? = null,
    val windSpeed: Float? = null,
    val windDeg: Int? = null,
    val weather: WeatherDetailsDb? = null
)