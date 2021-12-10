package com.example.myweatherapp.domain.models

data class CurrentWeather(
    val id: Long,
    val currentTemp: String,
    val status: String,
//    val dayTemp: String,
//    val nightTemp: String,
    val background: Int
)
