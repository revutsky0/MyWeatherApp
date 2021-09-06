package com.example.myweatherapp.pojo.oneCall

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.example.myweatherapp.getDateFromStamp
import com.example.myweatherapp.getDayOfWeekFromStamp
import com.example.myweatherapp.pojo.WeatherConverter

@Entity(tableName = "weather_daily")
@TypeConverters(WeatherConverter::class)
data class WeatherDaily(

    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @SerializedName("dt")
    @Expose
    val dt: Long? = null,

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
    val clouds: Int? = null
) {
    fun getDayNightTemp(): String {
        temp?.let {
            return "${it.day ?: "0"}°C / ${temp.night ?: '0'}°C"
        }
        return ""
    }

    fun getWeatherDescription(): String {
        if (weather == null || weather.isEmpty()) {
            return "none"
        }
        val item = weather[0]
        return item.description?.replaceFirstChar { char -> char.uppercase() }?:""
    }

    fun getDate() = getDateFromStamp(dt)

    fun getDayOfWeek() = getDayOfWeekFromStamp(dt).replaceFirstChar { char -> char.uppercase() }

}