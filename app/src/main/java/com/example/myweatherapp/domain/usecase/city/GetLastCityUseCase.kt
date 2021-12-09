package com.example.myweatherapp.domain.usecase.city

import com.example.myweatherapp.domain.repository.CityRepository
import com.example.myweatherapp.domain.repository.WeatherRepository

class GetLastCityUseCase(private val cityRep: CityRepository) {
    suspend operator fun invoke() = cityRep.getLastCity()
}