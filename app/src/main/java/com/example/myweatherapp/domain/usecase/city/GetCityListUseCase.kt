package com.example.myweatherapp.domain.usecase.city

import com.example.myweatherapp.domain.repository.CityRepository
import javax.inject.Inject

class GetCityListUseCase @Inject constructor(private val cityRepository: CityRepository) {
    suspend operator fun invoke(name: String) = cityRepository.getCityList(name)

}