package com.example.myweatherapp.data.mappers

import android.util.Log
import com.example.myweatherapp.R
import com.example.myweatherapp.data.database.dbmodels.CurrentWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.DailyWeatherDbModel
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem

class WeatherMapper {

    fun currentFromDbToDomain(current: CurrentWeatherDbModel): CurrentWeather {

        return CurrentWeather(
            id = 1,
            currentTemp = "",
            status = "",
            dayTemp = "",
            nightTemp = "",
            background = 1
        )
    }

    fun dailyFromDbToDomain(daily: DailyWeatherDbModel): DailyWeather {
        TODO()
    }

    fun dailyFromDbToDomainListItem(daily: DailyWeatherDbModel): DailyWeatherListItem {
        TODO()
    }

    fun getIcon(id: Int) = when (id) {
        800 -> R.drawable.sunny
        in 801..804 -> R.drawable.cloud
        in 600..699 -> R.drawable.show
        in 300..599 -> R.drawable.rainy
        in 200..299 -> R.drawable.thunder
        else -> R.drawable.sunny
    }

    fun getBackground(id: Int): Int {
        Log.d("MyApp", "bg id =  $id")
        return when (id) {
            800 -> R.drawable.sunny_bg
            801, 302 -> R.drawable.partly_cloudy_bg
            803, 804 -> R.drawable.clouds_bg
            in 600..699 -> R.drawable.snowfall_bg
            in 300..599 -> R.drawable.rain_bg
            in 200..299 -> R.drawable.thunderstorm_bg
            else -> R.drawable.sunny_bg
        }
    }

}