package com.example.myweatherapp.data.network.pojo

import androidx.room.TypeConverter
import com.example.myweatherapp.data.network.pojo.oneCall.FeelsLike
import com.example.myweatherapp.data.network.pojo.oneCall.Temp
import com.example.myweatherapp.data.network.pojo.oneCall.WeatherDetailsPojo
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
    fun fromWeatherDetails(weatherDetails: WeatherDetailsPojo): String {
        return Gson().toJson(weatherDetails)
    }

    @TypeConverter
    fun toWeatherDetails(weatherDetails: String): WeatherDetailsPojo {
        return Gson().fromJson(weatherDetails, WeatherDetailsPojo::class.java)
    }

    @TypeConverter
    fun fromWeatherDetailsList(list: List<WeatherDetailsPojo>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toWeatherDetailsList(list: String): List<WeatherDetailsPojo> {
        val result = mutableListOf<WeatherDetailsPojo>()
        val array = JSONArray(list)
        for (index: Int in 0 until array.length()) {
            val item = Gson().fromJson(array[index].toString(), WeatherDetailsPojo::class.java)
            result.add(item)
        }
        return result
    }

}