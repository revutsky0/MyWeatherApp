package com.example.myweatherapp.pojo.oneCall

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class WeatherCurrent(
    @SerializedName("dt")
    @Expose
    val dt: Int? = null,

    @SerializedName("sunrise")
    @Expose
    val sunrise: Int? = null,

    @SerializedName("sunset")
    @Expose
    val sunset: Int? = null,

    @SerializedName("temp")
    @Expose
    val temp: Double? = null,

    @SerializedName("feels_like")
    @Expose
    val feelsLike: Double? = null,

    @SerializedName("pressure")
    @Expose
    val pressure: Int? = null,

    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null,

    @SerializedName("dew_point")
    @Expose
    val dewPoint: Double? = null,

    @SerializedName("uvi")
    @Expose
    val uvi: Double? = null,

    @SerializedName("clouds")
    @Expose
    val clouds: Int? = null,

    @SerializedName("visibility")
    @Expose
    val visibility: Int? = null,

    @SerializedName("wind_speed")
    @Expose
    val windSpeed: Double? = null,

    @SerializedName("wind_deg")
    @Expose
    val windDeg: Int? = null,

    @SerializedName("wind_gust")
    @Expose
    val windGust: Double? = null,

    @SerializedName("weather")
    @Expose
    val weather: List<WeatherDetails>? = null
) {
    fun getTemperature() : String {
        temp?.let { return "$temp°C" }
        return "0"
    }

    fun getWeatherStatus() : String {
        if (weather==null||weather.isEmpty()) return ""
        val weatherItem = weather[0]
        return weatherItem.description?:""
    }

}