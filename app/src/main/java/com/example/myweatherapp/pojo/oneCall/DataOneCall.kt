package com.example.myweatherapp.pojo.oneCall

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class DataOneCall(
    @SerializedName("lat")
    @Expose
    val lat: Double? = null,

    @SerializedName("lon")
    @Expose
    val lon: Double? = null,

    @SerializedName("timezone")
    @Expose
    val timezone: String? = null,

    @SerializedName("timezone_offset")
    @Expose
    val timezoneOffset: Int? = null,

    @SerializedName("current")
    @Expose
    val current: WeatherCurrent? = null,

    @SerializedName("daily")
    @Expose
    val daily: List<WeatherDaily>? = null
)