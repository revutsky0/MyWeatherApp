package com.example.myweatherapp.domain.usecase.weather

import com.example.myweatherapp.domain.repository.WeatherRepository

class GetDailyWeatherListUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke() = repository.getDailyWeatherList()
}