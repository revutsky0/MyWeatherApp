package com.example.myweatherapp.domain.models

import java.io.Serializable

data class City(
    val name: String,
    val country: String,
    val lat: Float,
    val lon: Float
) : Serializable