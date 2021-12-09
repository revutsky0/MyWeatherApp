package com.example.myweatherapp.domain.repository

import com.example.myweatherapp.domain.models.City

interface CityRepository {
    suspend fun getCityList(name: String): List<City>
    fun getLastCity(): City?
}