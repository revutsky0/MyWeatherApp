package com.example.myweatherapp.data.database.converters

import androidx.room.TypeConverter
import com.example.myweatherapp.data.database.dbmodels.TempDb
import com.example.myweatherapp.data.database.dbmodels.WeatherDetailsDb
import com.example.myweatherapp.data.network.pojo.oneCall.FeelsLike
import com.google.gson.Gson
import org.json.JSONArray

class WeatherConverter {

    @TypeConverter
    fun fromTemp(temp: TempDb): String {
        return Gson().toJson(temp)
    }

    @TypeConverter
    fun toTemp(temp: String): TempDb {
        return Gson().fromJson(temp, TempDb::class.java)
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
    fun fromWeatherDetails(weatherDetails: WeatherDetailsDb): String {
        return Gson().toJson(weatherDetails)
    }

    @TypeConverter
    fun toWeatherDetails(weatherDetails: String): WeatherDetailsDb {
        return Gson().fromJson(weatherDetails, WeatherDetailsDb::class.java)
    }

    @TypeConverter
    fun fromWeatherDetailsList(list: List<WeatherDetailsDb>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toWeatherDetailsList(list: String): List<WeatherDetailsDb> {
        val result = mutableListOf<WeatherDetailsDb>()
        val array = JSONArray(list)
        for (index: Int in 0 until array.length()) {
            val item = Gson().fromJson(array[index].toString(), WeatherDetailsDb::class.java)
            result.add(item)
        }
        return result
    }

}