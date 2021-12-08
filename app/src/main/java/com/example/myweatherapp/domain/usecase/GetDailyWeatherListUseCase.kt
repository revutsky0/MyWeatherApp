package com.example.myweatherapp.domain.usecase

import com.example.myweatherapp.domain.repository.WeatherRepository

class GetDailyWeatherListUseCase(private val repository: WeatherRepository) {
    operator fun invoke() = repository.getDailyWeatherList()
}