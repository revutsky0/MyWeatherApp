package com.example.myweatherapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.myweatherapp.data.database.WeatherDao
import com.example.myweatherapp.data.mappers.WeatherMapper
import com.example.myweatherapp.data.workers.LoadWeatherWorker
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dao: WeatherDao,
    private val mapper: WeatherMapper,
    private val manager: WorkManager
) : WeatherRepository {

    private val workDelay = 10000L

    override fun getCurrentWeather(): LiveData<CurrentWeather?> {
        return MediatorLiveData<CurrentWeather>().apply {
            addSource(dao.getCurrentWeather()) {
                value = if (it != null) {
                    mapper.currentFromDbToDomain(it)
                } else null
            }
        }
    }

    override fun getDailyWeatherListLiveData(): LiveData<List<DailyWeatherListItem>> {
        return MediatorLiveData<List<DailyWeatherListItem>>().apply {
            addSource(dao.getDailyWeatherList()) { list ->
                value = list.map {
                    mapper.dailyFromDbToDomainListItem(it)
                }
            }
        }
    }

    override fun getDailyWeather(): LiveData<DailyWeather?> {
        return MediatorLiveData<DailyWeather>().apply {
            addSource(dao.getDailyWeather()) {
                value = if (it != null) {
                    mapper.dailyFromDbToDomain(it)
                } else null
            }
        }
    }

    override fun getDailyWeatherByDt(dt: Long): LiveData<DailyWeather> {
        return MediatorLiveData<DailyWeather>().apply {
            addSource(dao.getDailyWeatherByDt(dt)) {
                value = mapper.dailyFromDbToDomain(it)
            }
        }
    }

    override suspend fun startLoad(lat: Float, lon: Float) {
        manager.enqueueUniqueWork(
            LoadWeatherWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            LoadWeatherWorker.getWorkRequest(workDelay, lat, lon)
        )
    }

}