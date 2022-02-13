package com.example.myweatherapp.domain.usecase.city

import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.CityRepository
import javax.inject.Inject

class GetCityFromLocationUseCase @Inject constructor(private val repository: CityRepository) {
    operator fun invoke(): City {
        TODO()
    }
}