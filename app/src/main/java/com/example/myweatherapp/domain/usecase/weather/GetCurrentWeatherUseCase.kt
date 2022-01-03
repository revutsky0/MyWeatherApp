package com.example.myweatherapp.domain.usecase.weather

import com.example.myweatherapp.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke() = repository.getCurrentWeather()
}