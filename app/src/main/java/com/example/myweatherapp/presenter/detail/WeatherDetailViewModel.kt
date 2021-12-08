package com.example.myweatherapp.presenter.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myweatherapp.data.repository.WeatherRepositoryImpl
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.usecase.GetDailyWeatherUseCase

class WeatherDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WeatherRepositoryImpl(application)
    private val getDailyWeather = GetDailyWeatherUseCase(repository)
    private val _weather = MutableLiveData<DailyWeather>()
    val weather: LiveData<DailyWeather> = _weather

    fun getWeather(dt: Long) {
        val current = getDailyWeather(dt)
        _weather.postValue(current)
    }

}