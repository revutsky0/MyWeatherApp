package com.example.myweatherapp.domain.usecase.weather

import com.example.myweatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetDailyWeatherListUseCase @Inject constructor(private val repository: WeatherRepository) {
    operator fun invoke() = repository.getDailyWeatherListLiveData()
}