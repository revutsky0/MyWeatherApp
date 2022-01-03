package com.example.myweatherapp.data.network.pojo.oneCall

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataOneCall(
    @SerializedName("lat")
    @Expose
    val lat: Float? = null,

    @SerializedName("lon")
    @Expose
    val lon: Float? = null,

    @SerializedName("timezone")
    @Expose
    val timezone: String? = null,

    @SerializedName("timezone_offset")
    @Expose
    val timezoneOffset: Int? = null,

    @SerializedName("current")
    @Expose
    val current: WeatherCurrentPojo? = null,

    @SerializedName("daily")
    @Expose
    val daily: List<WeatherDailyPojo>? = null
)