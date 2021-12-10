package com.example.myweatherapp.data.mappers

import android.util.Log
import com.example.myweatherapp.R
import com.example.myweatherapp.data.database.dbmodels.CurrentWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.DailyWeatherDbModel
import com.example.myweatherapp.domain.models.CurrentWeather
import com.example.myweatherapp.domain.models.DailyWeather
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeatherMapper {

    fun currentFromDbToDomain(current: CurrentWeatherDbModel) = CurrentWeather(
        id = current.dt,
        currentTemp = "${current.temp?.roundToInt()}",
        status = current.weather?.description ?: "",
        background = if (current.weather != null) {
            getBackground(current.weather.id)
        } else 0
    )

    fun dailyFromDbToDomain(daily: DailyWeatherDbModel) = DailyWeather(
        id = daily.dt,
        dayOfWeek = getDayOfWeekFromStamp(daily.dt),
        date = getDateFromStamp(daily.dt),
        dayTemp = "${daily.temp?.day?.roundToInt()}",
        nightTemp = "${daily.temp?.night?.roundToInt()}",
        status = "${daily.weather?.description}",
        clouds = daily.clouds ?: 0,
        humidity = daily.humidity ?: 0,
        precipitation = daily.precipitation ?: 0,
        pressure = daily.pressure ?: 0,
        wind = daily.windSpeed?.roundToInt() ?: 0
    )

    fun dailyFromDbToDomainListItem(daily: DailyWeatherDbModel) = DailyWeatherListItem(
        id = daily.dt,
        dayOfWeek = getDayOfWeekFromStamp(daily.dt),
        date = getDateFromStamp(daily.dt),
        dayTemp = "${daily.temp?.day?.roundToInt()}",
        nightTemp = "${daily.temp?.night?.roundToInt()}",
        status = "${daily.weather?.description}",
        windSpeed = daily.windSpeed ?: 0f,
        windUnits = "м/с",
        icon = if (daily.weather != null) {
            getIcon(daily.weather.id)
        } else getIcon(0)
    )

    private fun getIcon(id: Int) = when (id) {
        800 -> R.drawable.sunny
        in 801..804 -> R.drawable.cloud
        in 600..699 -> R.drawable.show
        in 300..599 -> R.drawable.rainy
        in 200..299 -> R.drawable.thunder
        else -> R.drawable.sunny
    }

    private fun getBackground(id: Int): Int {
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

    private fun getDateFromStamp(stamp: Long?) = getFromStamp(stamp, "dd MMMM")

    private fun getDayOfWeekFromStamp(stamp: Long?) = getFromStamp(stamp, "E")

    private fun getFromStamp(stamp: Long?, pattern: String): String {
        if (stamp == null) {
            return ""
        }
        val date = Date(stamp * 1000)
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()

        return simpleDateFormat.format(date)
    }

}