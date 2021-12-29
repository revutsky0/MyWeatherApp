package com.example.myweatherapp.domain.repository

import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem

interface WeatherRepository {
    suspend fun getCurrentWeather(): CurrentWeather?
    suspend fun getDailyWeatherList(): List<DailyWeatherListItem>
    suspend fun getDailyWeather(dt: Long): DailyWeather
    suspend fun startLoad(lat: Float, lon: Float)
}