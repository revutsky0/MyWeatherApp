package com.example.myweatherapp.data.repository

import android.content.Context
import com.example.myweatherapp.data.mappers.NetworkMapper
import com.example.myweatherapp.data.network.api.ApiFactory
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.CityRepository

class CityRepositoryImpl(context: Context) : CityRepository {

    companion object {
        private const val SHARED_PREFS_NAME = "WeatherParams"
        private const val CITY_PARAM_NAME = "CityName"
        private const val CITY_PARAM_COUNTRY = "CityCountry"
        private const val CITY_PARAM_LAT = "CityLat"
        private const val CITY_PARAM_LON = "CityLon"
    }

    private val prefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    private val mapper = NetworkMapper()

    override suspend fun getCityList(name: String): List<City> =
        ApiFactory.apiService.getDataOfCity(name).map { mapper.cityToModel(it) }


    override fun getLastCity(): City? {
        with(prefs) {
            return if (!contains(CITY_PARAM_NAME) ||
                !contains(CITY_PARAM_COUNTRY) ||
                !contains(CITY_PARAM_LAT) ||
                !contains(CITY_PARAM_LON)
            ) {
                null
            } else {
                City(
                    name = getString(CITY_PARAM_NAME, "") ?: "",
                    country = getString(CITY_PARAM_COUNTRY, "") ?: "",
                    lat = getFloat(CITY_PARAM_LAT, 0f),
                    lon = getFloat(CITY_PARAM_LON, 0f)
                )
            }
        }
    }
}