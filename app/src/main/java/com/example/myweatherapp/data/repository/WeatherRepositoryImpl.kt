package com.example.myweatherapp.data.repository

import android.app.Application
import com.example.myweatherapp.data.database.WeatherDatabase
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(application: Application) : WeatherRepository {

    private val database = WeatherDatabase.getInstance(application)

    override fun getCurrentWeather(): CurrentWeather {
        TODO("Not yet implemented")
    }

    override fun getDailyWeatherList(): List<DailyWeatherListItem> {
        TODO("Not yet implemented")
    }

    override fun getDailyWeather(dt: Long): DailyWeather {
        TODO("Not yet implemented")
    }

}