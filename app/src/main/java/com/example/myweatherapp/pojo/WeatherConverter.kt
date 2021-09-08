package com.example.myweatherapp.pojo

import androidx.room.TypeConverter
import com.example.myweatherapp.pojo.oneCall.FeelsLike
import com.example.myweatherapp.pojo.oneCall.Temp
import com.example.myweatherapp.pojo.oneCall.WeatherDetails
import com.google.gson.Gson
import org.json.JSONArray

class WeatherConverter {

    @TypeConverter
    fun fromTemp(temp: Temp): String {
        return Gson().toJson(temp)
    }

    @TypeConverter
    fun toTemp(temp: String): Temp {
        return Gson().fromJson(temp, Temp::class.java)
    }

    @TypeConverter
    fun fromFeelsLike(feelsLike: FeelsLike): String {
        return Gson().toJson(feelsLike)
    }

    @TypeConverter
    fun toFeelsLike(feelsLike: String): FeelsLike {
        return Gson().fromJson(feelsLike, FeelsLike::class.java)
    }

    @TypeConverter
    fun fromWeatherDetails(weatherDetails: WeatherDetails): String {
        return Gson().toJson(weatherDetails)
    }

    @TypeConverter
    fun toWeatherDetails(weatherDetails: String): WeatherDetails {
        return Gson().fromJson(weatherDetails, WeatherDetails::class.java)
    }

    @TypeConverter
    fun fromWeatherDetailsList(list: List<WeatherDetails>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toWeatherDetailsList(list: String): List<WeatherDetails> {
        val result = mutableListOf<WeatherDetails>()
        val array = JSONArray(list)
        for (index: Int in 0 until array.length()) {
            val item = Gson().fromJson(array[index].toString(), WeatherDetails::class.java)
            result.add(item)
        }
        return result
    }

}