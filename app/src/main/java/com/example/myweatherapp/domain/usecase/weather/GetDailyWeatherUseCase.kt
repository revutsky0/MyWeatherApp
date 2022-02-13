package com.example.myweatherapp.domain.usecase.weather

import com.example.myweatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetDailyWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke() = repository.getDailyWeather()
    fun byDt(dt: Long) = repository.getDailyWeatherByDt(dt)
}