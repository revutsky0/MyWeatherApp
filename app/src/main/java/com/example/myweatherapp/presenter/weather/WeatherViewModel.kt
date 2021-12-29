package com.example.myweatherapp.presenter.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myweatherapp.data.repository.CityRepositoryImpl
import com.example.myweatherapp.data.repository.WeatherRepositoryImpl
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.usecase.city.GetCityWeatherUseCase
import com.example.myweatherapp.domain.usecase.city.SaveLastCityUseCase
import com.example.myweatherapp.domain.usecase.weather.GetCurrentWeatherUseCase
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherListUseCase
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val weatherRepository = WeatherRepositoryImpl(application)
    private val cityRepository = CityRepositoryImpl(application)

    private val getCurrentWeather = GetCurrentWeatherUseCase(weatherRepository)
    private val getDailyWeather = GetDailyWeatherUseCase(weatherRepository)
    private val getDailyWeatherList = GetDailyWeatherListUseCase(weatherRepository)
    private val getCityWeather = GetCityWeatherUseCase(weatherRepository)
    private val saveLastCity = SaveLastCityUseCase(cityRepository)

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

