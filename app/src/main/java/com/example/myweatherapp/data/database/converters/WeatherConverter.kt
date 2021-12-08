package com.example.myweatherapp.data.database.converters

import androidx.room.TypeConverter
import com.example.myweatherapp.data.database.dbmodels.WeatherDetailsDbModel
import com.example.myweatherapp.data.network.pojo.oneCall.FeelsLike
import com.example.myweatherapp.data.network.pojo.oneCall.Temp
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
    fun fromWeatherDetails(weatherDetails: WeatherDetailsDbModel): String {
        return Gson().toJson(weatherDetails)
    }

    @TypeConverter
    fun toWeatherDetails(weatherDetails: String): WeatherDetailsDbModel {
        return Gson().fromJson(weatherDetails, WeatherDetailsDbModel::class.java)
    }

    @TypeConverter
    fun fromWeatherDetailsList(list: List<WeatherDetailsDbModel>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toWeatherDetailsList(list: String): List<WeatherDetailsDbModel> {
        val result = mutableListOf<WeatherDetailsDbModel>()
        val array = JSONArray(list)
        for (index: Int in 0 until array.length()) {
            val item = Gson().fromJson(array[index].toString(), WeatherDetailsDbModel::class.java)
            result.add(item)
        }
        return result
    }

}