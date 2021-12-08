package com.example.myweatherapp.domain.models

data class DailyWeather(
    val id: Long,
    val dayOfWeek : String,
    val date: String,
    val dayTemp: String,
    val nightTemp: String,
    val status: String,
    val clouds: Int,
    val humidity: Int,
    val precipitation: Int,
    val pressure: Int,
    val wind: Int
)