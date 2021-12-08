package com.example.myweatherapp.data.network.pojo.oneCall

import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myweatherapp.data.database.converters.WeatherConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@TypeConverters(WeatherConverter::class)
data class WeatherDailyPojo(

    @PrimaryKey
    @SerializedName("dt")
    @Expose
    var dt: Long,

    @SerializedName("sunrise")
    @Expose
    val sunrise: Int? = null,

    @SerializedName("sunset")
    @Expose
    val sunset: Int? = null,

    @SerializedName("moonrise")
    @Expose
    val moonrise: Int? = null,

    @SerializedName("moonset")
    @Expose
    val moonset: Int? = null,

    @SerializedName("moon_phase")
    @Expose
    val moonPhase: Float? = null,

    @SerializedName("temp")
    @Expose
    val temp: Temp? = null,

    @SerializedName("feels_like")
    @Expose
    val feelsLike: FeelsLike? = null,

    @SerializedName("pressure")
    @Expose
    val pressure: Int? = null,

    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null,

    @SerializedName("dew_point")
    @Expose
    val dewPoint: Float? = null,

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
    val weather: List<WeatherDetailsPojo>? = null,

    @SerializedName("clouds")
    @Expose
    val clouds: Int? = null,

    @SerializedName("pop")
    @Expose
    val pop: Float? = null

)