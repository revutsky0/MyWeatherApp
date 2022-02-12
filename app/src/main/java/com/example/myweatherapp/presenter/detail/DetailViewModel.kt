package com.example.myweatherapp.presenter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.data.repository.WeatherRepositoryImpl
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDailyWeather: GetDailyWeatherUseCase
) : ViewModel() {

    private val _weather = MutableLiveData<DailyWeather>()
    val weather: LiveData<DailyWeather> = _weather

    fun getWeather(dt: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            val current = getDailyWeather(dt)
            _weather.postValue(current)
        }
    }

}