package com.example.myweatherapp.data.repository

import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.CityRepository

class CityRepositoryImpl : CityRepository {



    override fun getCityList(name: String): List<City> {
        TODO("Not yet implemented")
    }

    override fun getLastCity(): City {
        TODO("Not yet implemented")
    }
}