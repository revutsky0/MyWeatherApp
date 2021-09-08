package com.example.myweatherapp.pojo.oneCall

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myweatherapp.DEGREE_STRING
import com.example.myweatherapp.pojo.WeatherConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

@Entity(tableName = "weather_current")
@TypeConverters(WeatherConverter::class)
data class WeatherCurrent(

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

    fun getTemperature() = "${temp?.roundToInt() ?: 0}$DEGREE_STRING"

    fun getWeatherStatus(): String {
        if (weather == null || weather.isEmpty()) return ""
        val weatherItem = weather[0]
        return weatherItem.description?.replaceFirstChar { char -> char.uppercase() } ?: ""
    }

    fun getWeatherBackground(): Int {
        if (weather == null || weather.isEmpty()) return 0
        val weatherItem = weather[0]
        return weatherItem.getBackground()
    }

}