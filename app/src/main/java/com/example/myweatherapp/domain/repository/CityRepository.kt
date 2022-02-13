package com.example.myweatherapp.domain.repository

import android.location.Location
import com.example.myweatherapp.domain.models.City

interface CityRepository {
    suspend fun getCityList(name: String): List<City>
    suspend fun getCityFromLocation(location: Location) : List<City>
    fun getLastCity(): City?
    fun saveLastCity(city: City)
}