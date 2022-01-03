package com.example.myweatherapp.data.repository

import android.content.Context
import com.example.myweatherapp.data.mappers.NetworkMapper
import com.example.myweatherapp.data.network.api.ApiFactory
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.CityRepository
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class CityRepositoryImpl(context: Context) : CityRepository {

    companion object {
        private const val SHARED_PREFS_NAME = "WeatherParams"
        private const val CITY_PARAM_NAME = "CityName"
        private const val CITY_PARAM_COUNTRY = "CityCountry"
        private const val CITY_PARAM_LAT = "CityLat"
        private const val CITY_PARAM_LON = "CityLon"
        private const val CITY_PARAM_LOCAL_NAMES = "LocaleNames"
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
                    lon = getFloat(CITY_PARAM_LON, 0f),
                    localNames = try {
                        JSONObject(getString(CITY_PARAM_LOCAL_NAMES, "{}") ?: "{}")
                    } catch (e: Exception) {
                        null
                    }
                )
            }
        }

    }

    override fun saveLastCity(city: City) {
        prefs.edit()
            .putString(CITY_PARAM_NAME, city.name)
            .putString(CITY_PARAM_COUNTRY, city.country)
            .putFloat(CITY_PARAM_LAT, city.lat)
            .putFloat(CITY_PARAM_LON, city.lon)
            .putString(CITY_PARAM_LOCAL_NAMES, city.localNames.toString())
            .apply()
    }
}