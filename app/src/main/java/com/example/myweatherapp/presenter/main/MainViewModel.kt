package com.example.myweatherapp.presenter.main

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myweatherapp.data.repository.CityRepositoryImpl
import com.example.myweatherapp.data.repository.WeatherRepositoryImpl
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.domain.usecase.city.GetCityListUseCase
import com.example.myweatherapp.domain.usecase.city.GetCityWeatherUseCase
import com.example.myweatherapp.domain.usecase.city.GetLastCityUseCase
import com.example.myweatherapp.domain.usecase.weather.GetCurrentWeatherUseCase
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherListUseCase
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val weatherRepository = WeatherRepositoryImpl(application)
    private val cityRepository = CityRepositoryImpl(application)

    private val getCurrentWeather = GetCurrentWeatherUseCase(weatherRepository)
    private val getDailyWeather = GetDailyWeatherUseCase(weatherRepository)
    private val getDailyWeatherList = GetDailyWeatherListUseCase(weatherRepository)
    private val getLastCity = GetLastCityUseCase(cityRepository)
    private val getCityWeather = GetCityWeatherUseCase(weatherRepository)
    private val getCityList = GetCityListUseCase(cityRepository)

    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather> = _currentWeather

    private val _currentDailyWeather = MutableLiveData<DailyWeather>()
    val currentDailyWeather: LiveData<DailyWeather> = _currentDailyWeather

    private val _weeklyWeather = MutableLiveData<List<DailyWeatherListItem>>()
    val weeklyWeather: LiveData<List<DailyWeatherListItem>> = _weeklyWeather

    private val scope = CoroutineScope(Dispatchers.IO)
    private var city: City? = null

    init {
        scope.launch {
            loadData(city = getLastCity())
        }
    }

    fun findCity(name: String) {
        scope.launch {
            val cityList = getCityList(name)
            loadData(cityList[0])
        }
    }

    private suspend fun loadData(city: City?) {
        if (city == null) return
        this.city = city
        getCityWeather(city)
        val current = getCurrentWeather()
        val daily = getDailyWeather(current.id)
        val dailyList = getDailyWeatherList()
        _currentWeather.postValue(current)
        _currentDailyWeather.postValue(daily)
        _weeklyWeather.postValue(dailyList)
    }

    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}

