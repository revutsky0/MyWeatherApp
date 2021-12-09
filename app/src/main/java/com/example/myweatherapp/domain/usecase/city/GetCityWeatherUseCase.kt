package com.example.myweatherapp.domain.usecase.city

import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.WeatherRepository

class GetCityWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(city: City) =
        repository.startLoad(
            lat = city.lat,
            lon = city.lon
        )
}