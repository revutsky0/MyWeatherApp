package com.example.myweatherapp.detailActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myweatherapp.database.WeatherDatabase
import com.example.myweatherapp.pojo.oneCall.WeatherDaily

class WeatherDetailViewModel(application: Application) : AndroidViewModel(application) {

    val database = WeatherDatabase.getInstance(application)
    lateinit var weather: LiveData<WeatherDaily>

    fun getWeather(id: Int): LiveData<WeatherDaily> {
        weather = database.dao().getDailyWeatherById(id)
        return weather
    }

}