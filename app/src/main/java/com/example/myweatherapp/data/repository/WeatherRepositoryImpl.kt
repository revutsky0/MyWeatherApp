package com.example.myweatherapp.data.repository

import android.app.Application
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.myweatherapp.data.database.WeatherDatabase
import com.example.myweatherapp.data.mappers.WeatherMapper
import com.example.myweatherapp.data.workers.LoadWeatherWorker
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(application: Application) : WeatherRepository {

    private val database = WeatherDatabase.getInstance(application)
    private val mapper = WeatherMapper()
    private val manager = WorkManager.getInstance(application)
    private val workDelay = 5000L // В секундах!

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

    override suspend fun startLoad(lat: Float, lon: Float) {
        manager.enqueueUniqueWork(
            LoadWeatherWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            LoadWeatherWorker.getWorkRequest(workDelay, lat, lon)
        )
    }

}