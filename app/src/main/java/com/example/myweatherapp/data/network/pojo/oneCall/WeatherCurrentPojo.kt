package com.example.myweatherapp.data.network.pojo.oneCall

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherCurrentPojo(

    @SerializedName("dt")
    @Expose
    var dt: Long,

    @SerializedName("sunrise")
    @Expose
    val sunrise: Int? = null,

    @SerializedName("sunset")
    @Expose
    val sunset: Int? = null,

    @SerializedName("temp")
    @Expose
    val temp: Float? = null,

    @SerializedName("feels_like")
    @Expose
    val feelsLike: Float? = null,

    @SerializedName("pressure")
    @Expose
    val pressure: Int? = null,

    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null,

    @SerializedName("dew_point")
    @Expose
    val dewPoint: Float? = null,

    @SerializedName("uvi")
    @Expose
    val uvi: Float? = null,

    @SerializedName("clouds")
    @Expose
    val clouds: Int? = null,

    @SerializedName("visibility")
    @Expose
    val visibility: Int? = null,

    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Float? = null,

    @SerializedName("wind_deg")
    @Expose
    val windDeg: Int? = null,

    @SerializedName("wind_gust")
    @Expose
    val windGust: Float? = null,

    @SerializedName("weather")
    @Expose
    val weather: List<WeatherDetailsPojo>? = null
)