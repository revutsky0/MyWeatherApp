package com.example.myweatherapp.domain.models

import com.example.myweatherapp.DEGREE_STRING
import kotlin.math.roundToInt

data class CurrentWeather(
    val id: Long,
    val currentTemp: String,
    val status: String,
    val dayTemp: String,
    val nightTemp: String,
    val background: Int
) {
//    fun getTemperature() = "${temp?.roundToInt() ?: 0}$DEGREE_STRING"
//
//    fun getWeatherStatus(): String {
//        if (weather == null || weather.isEmpty()) return ""
//        val weatherItem = weather[0]
//        return weatherItem.description?.replaceFirstChar { char -> char.uppercase() } ?: ""
//    }
//
//    fun getWeatherBackground(): Int {
//        if (weather == null || weather.isEmpty()) return 0
//        val weatherItem = weather[0]
//        return weatherItem.getBackground()
//    }
}
