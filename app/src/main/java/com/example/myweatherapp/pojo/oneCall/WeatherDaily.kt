package com.example.myweatherapp.pojo.oneCall

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myweatherapp.DEGREE_STRING
import com.example.myweatherapp.getDateFromStamp
import com.example.myweatherapp.getDayAndDateFromStamp
import com.example.myweatherapp.getDayOfWeekFromStamp
import com.example.myweatherapp.pojo.WeatherConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

@Entity(tableName = "weather_daily")
@TypeConverters(WeatherConverter::class)
data class WeatherDaily(

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
    val moonPhase: Double? = null,

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
    val dewPoint: Double? = null,

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
    val weather: List<WeatherDetails>? = null,

    @SerializedName("clouds")
    @Expose
    val clouds: Int? = null,

    @SerializedName("pop")
    @Expose
    val pop: Double? = null

) {

    fun getDayNightTemp(): String {
        temp?.let {
            return "${it.day?.roundToInt() ?: "0"}° / ${temp.night?.roundToInt() ?: '0'}$DEGREE_STRING"
        }
        return ""
    }

    fun getDayTemp() = "${temp?.day?.roundToInt() ?: "0"}$DEGREE_STRING"

    fun getNightTemp() = "${temp?.night?.roundToInt() ?: "0"}$DEGREE_STRING"

    fun getWeatherDescription(): String {
        if (weather == null || weather.isEmpty()) {
            return "none"
        }
        val item = weather[0]
        return item.description?.replaceFirstChar { char -> char.uppercase() } ?: ""
    }

    fun getWeatherIcon(): Int {
        if (weather == null || weather.isEmpty()) {
            return 0
        }
        val item = weather[0]
        return item.getIcon()
    }

    fun getWeatherBackground(): Int {
        if (weather == null || weather.isEmpty()) {
            return 0
        }
        val item = weather[0]
        return item.getBackground()
    }

    fun getDate() = getDateFromStamp(dt)

    fun getDayOfWeek() =
        getDayOfWeekFromStamp(dt).replaceFirstChar { char -> char.uppercase() }

    fun getDayAndDate() = getDayAndDateFromStamp(dt)

    fun getCloudsValue() = "${clouds ?: 0}%"

    fun getPressures() = "${pressure ?: 0}"

    fun getWind() = "${windSpeed?.roundToInt() ?: 0}"

    fun getPrecipitation() = "${pop?.times(100)?.roundToInt() ?: 0}%"

    fun getHumidityValue() = "${humidity ?: 0}%"
}