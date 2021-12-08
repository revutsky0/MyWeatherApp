package com.example.myweatherapp.domain.usecase

import com.example.myweatherapp.domain.repository.WeatherRepository

class GetDailyWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(dt: Long) = repository.getDailyWeather(dt)
}