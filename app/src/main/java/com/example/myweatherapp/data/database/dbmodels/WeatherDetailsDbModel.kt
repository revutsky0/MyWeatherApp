package com.example.myweatherapp.data.database.dbmodels

import java.io.Serializable

data class WeatherDetailsDbModel(
    val id: Int,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null

) : Serializable