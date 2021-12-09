package com.example.myweatherapp.domain.usecase.city

import com.example.myweatherapp.domain.repository.CityRepository
import com.example.myweatherapp.domain.repository.WeatherRepository

class GetLastCityUseCase(
    private val cityRep: CityRepository,
    private val weatherRep: WeatherRepository
) {
    suspend operator fun invoke(): Boolean {
        val city = cityRep.getLastCity()
        return if (city != null) {
            weatherRep.startLoad(lat = city.lat, lon = city.lon)
            true
        } else false
    }
}