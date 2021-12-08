package com.example.myweatherapp.domain.repository

import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem

interface WeatherRepository {
    fun getCurrentWeather(): CurrentWeather
    fun getDailyWeatherList(): List<DailyWeatherListItem>
    fun getDailyWeather(dt: Long): DailyWeather
}