package com.example.myweatherapp.presenter.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.data.repository.WeatherRepositoryImpl
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: WeatherRepositoryImpl,
) : ViewModel() {

    private val getDailyWeather = GetDailyWeatherUseCase(repository)
    private val _weather = MutableLiveData<DailyWeather>()
    val weather: LiveData<DailyWeather> = _weather

    fun getWeather(dt: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val current = getDailyWeather(dt)
            _weather.postValue(current)
        }
    }

}