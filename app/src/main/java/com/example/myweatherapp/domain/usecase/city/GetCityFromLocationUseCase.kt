package com.example.myweatherapp.domain.usecase.city

import android.location.Location
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.CityRepository
import javax.inject.Inject

class GetCityFromLocationUseCase @Inject constructor(private val repository: CityRepository) {
    suspend operator fun invoke(location: Location): List<City> {
        return repository.getCityFromLocation(location)
    }
}