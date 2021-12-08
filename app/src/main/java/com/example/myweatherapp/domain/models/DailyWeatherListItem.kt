package com.example.myweatherapp.domain.models

data class DailyWeatherListItem(
    val id: Long,
    val dayOfWeek: String,
    val date: String,
    val dayTemp: String,
    val status: String,
    val nightTemp: String,
    val windSpeed: Float,
    val windUnits: String,
    val icon: Int
)