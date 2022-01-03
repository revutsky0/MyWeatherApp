package com.example.myweatherapp.domain.usecase.city

import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.CityRepository

class SaveLastCityUseCase(private val repository: CityRepository) {
    operator fun invoke(city: City) = repository.saveLastCity(city)
}