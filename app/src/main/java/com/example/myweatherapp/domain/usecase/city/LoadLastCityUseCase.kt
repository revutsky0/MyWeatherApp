package com.example.myweatherapp.domain.usecase.city

import com.example.myweatherapp.domain.repository.CityRepository
import com.example.myweatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class LoadLastCityUseCase @Inject constructor(private val cityRep: CityRepository) {
    operator fun invoke() = cityRep.getLastCity()
}