package com.example.myweatherapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.myweatherapp.data.database.WeatherDatabase
import com.example.myweatherapp.data.mappers.NetworkMapper
import com.example.myweatherapp.data.network.api.ApiFactory
import java.util.concurrent.TimeUnit

class LoadWeatherWorker(private val appContext: Context, params: WorkerParameters) :
    CoroutineWorker(
        appContext,
        params
    ) {

    companion object {

        const val NAME = "WEATHER_LOADER"
        private const val LAT_PARAM = "lat"
        private const val LON_PARAM = "lon"

        fun getWorkRequest(delay: Long, lan: Float, lot: Float) =
            PeriodicWorkRequestBuilder<LoadWeatherWorker>(delay, TimeUnit.SECONDS)
                .setInputData(
                    workDataOf(
                        LAT_PARAM to lan,
                        LON_PARAM to lot
                    ),
                )
                .build()
    }

    private val mapper = NetworkMapper()

    override suspend fun doWork(): Result {
        val database = WeatherDatabase.getInstance(context = appContext)
        val lat = inputData.getFloat(LAT_PARAM, 0f)
        val lon = inputData.getFloat(LON_PARAM, 0f)
        val data = ApiFactory.apiService.getWeather(lat, lon)
        val daily = data.daily?.map { mapper.dailyToDbModel(it) }
        val current = if (data.current != null) {
            mapper.currentToDbModel(data.current)
        } else null
        daily?.let { database.dao().insertDailyWeather(daily) }
        current?.let { database.dao().insertCurrentWeather(current) }
        return Result.success()
    }
}