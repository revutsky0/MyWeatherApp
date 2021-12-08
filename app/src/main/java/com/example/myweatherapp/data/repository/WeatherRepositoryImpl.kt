package com.example.myweatherapp.data.repository

import android.app.Application
import com.example.myweatherapp.data.database.WeatherDatabase
import com.example.myweatherapp.data.mappers.WeatherMapper
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WeatherRepositoryImpl(application: Application) : WeatherRepository {

    private val database = WeatherDatabase.getInstance(application)
    private val mapper = WeatherMapper()

    override suspend fun getCurrentWeather(): CurrentWeather {
        val dbModel = database.dao().getCurrentWeather()
        return mapper.currentFromDbToDomain(dbModel)
    }

    override suspend fun getDailyWeatherList(): List<DailyWeatherListItem> {
        val dbModel = database.dao().getDailyWeatherList()
        return dbModel.map { mapper.dailyFromDbToDomainListItem(it) }
    }

    override suspend fun getDailyWeather(dt: Long): DailyWeather {
        val dbModel = database.dao().getDailyWeatherByDt(dt)
        return mapper.dailyFromDbToDomain(dbModel)
    }

}