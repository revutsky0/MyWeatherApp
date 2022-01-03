package com.example.myweatherapp.data.database.dbmodels

data class WeatherDetailsDb(
    val id: Int,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)