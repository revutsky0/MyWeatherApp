package com.example.myweatherapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem

interface WeatherRepository {
    fun getCurrentWeather(): LiveData<CurrentWeather>
    fun getDailyWeatherListLiveData(): LiveData<List<DailyWeatherListItem>>
    fun getDailyWeather(): LiveData<DailyWeather>
    fun getDailyWeatherByDt(dt: Long): LiveData<DailyWeather>
    suspend fun startLoad(lat: Float, lon: Float)
}