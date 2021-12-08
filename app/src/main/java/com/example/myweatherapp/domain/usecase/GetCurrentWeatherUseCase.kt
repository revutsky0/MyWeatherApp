package com.example.myweatherapp.domain.usecase

import com.example.myweatherapp.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(private val repository: WeatherRepository) {
    operator fun invoke() = repository.getCurrentWeather()
}