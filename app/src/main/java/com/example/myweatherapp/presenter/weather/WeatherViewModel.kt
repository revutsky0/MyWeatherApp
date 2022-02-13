package com.example.myweatherapp.presenter.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.usecase.city.GetCityWeatherUseCase
import com.example.myweatherapp.domain.usecase.city.SaveLastCityUseCase
import com.example.myweatherapp.domain.usecase.weather.GetCurrentWeatherUseCase
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherListUseCase
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeather: GetCurrentWeatherUseCase,
    private val getDailyWeather: GetDailyWeatherUseCase,
    private val getDailyWeatherList: GetDailyWeatherListUseCase,
    private val getCityWeather: GetCityWeatherUseCase,
    private val saveLastCity: SaveLastCityUseCase
) : ViewModel() {


    val currentWeather: LiveData<CurrentWeather> = getCurrentWeather()
    val currentDailyWeather: LiveData<DailyWeather> = getDailyWeather()
    val weeklyWeather: LiveData<List<DailyWeatherListItem>> = getDailyWeatherList()


    private val scope = CoroutineScope(Dispatchers.IO)

    fun loadCityWeather(city: City?) {
        if (city == null) return
        scope.launch {
            loadData(city)
        }
    }

    private suspend fun loadData(city: City) {
        saveLastCity(city)
        getCityWeather(city)
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}

