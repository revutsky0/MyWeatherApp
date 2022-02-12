package com.example.myweatherapp.presenter.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.repository.CityRepository
import com.example.myweatherapp.domain.repository.WeatherRepository
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

    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather> = _currentWeather

    private val _currentDailyWeather = MutableLiveData<DailyWeather>()
    val currentDailyWeather: LiveData<DailyWeather> = _currentDailyWeather

    private val _weeklyWeather = MutableLiveData<List<DailyWeatherListItem>>()
    val weeklyWeather: LiveData<List<DailyWeatherListItem>> = _weeklyWeather

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
        val current = getCurrentWeather()
        current?.let {
            val daily = getDailyWeather(current.id)
            _currentWeather.postValue(it)
            _currentDailyWeather.postValue(daily)
        }
        val dailyList = getDailyWeatherList()
        _weeklyWeather.postValue(dailyList)
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}

