package com.example.myweatherapp.data.repository

import android.app.Application
import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.myweatherapp.data.database.AppDatabase
import com.example.myweatherapp.data.mappers.WeatherMapper
import com.example.myweatherapp.data.workers.LoadWeatherWorker
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database : AppDatabase,
    private val mapper : WeatherMapper,
    private val manager: WorkManager
) : WeatherRepository {

    private val workDelay = 10000L

    override suspend fun getCurrentWeather(): CurrentWeather? {
        val dbModel = database.dao().getCurrentWeather()
        return if (dbModel != null) {
            mapper.currentFromDbToDomain(dbModel)
        } else {
            null
        }
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