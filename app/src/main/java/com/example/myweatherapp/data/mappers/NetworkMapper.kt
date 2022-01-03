package com.example.myweatherapp.data.mappers

import com.example.myweatherapp.data.database.dbmodels.CurrentWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.DailyWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.TempDb
import com.example.myweatherapp.data.database.dbmodels.WeatherDetailsDb
import com.example.myweatherapp.data.network.pojo.city.DataCity
import com.example.myweatherapp.data.network.pojo.city.LocalNamesData
import com.example.myweatherapp.data.network.pojo.oneCall.Temp
import com.example.myweatherapp.data.network.pojo.oneCall.WeatherCurrentPojo
import com.example.myweatherapp.data.network.pojo.oneCall.WeatherDailyPojo
import com.example.myweatherapp.data.network.pojo.oneCall.WeatherDetailsPojo
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.models.LocalNames
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

class NetworkMapper {

    private val calendar = Calendar.getInstance()

    fun dailyToDbModel(pojo: WeatherDailyPojo) = DailyWeatherDbModel(
        dt = dateToId(pojo.dt),
        temp = if (pojo.temp != null) {
            tempToDb(pojo.temp)
        } else null,
        pressure = pojo.pressure,
        humidity = pojo.humidity,
        windSpeed = pojo.windSpeed,
        windDeg = pojo.windDeg,
        weather = if (pojo.weather != null) {
            detailsToDbModel(pojo.weather[0])
        } else null,
        clouds = pojo.clouds
    )

    fun currentToDbModel(pojo: WeatherCurrentPojo) = CurrentWeatherDbModel(
        dt = dateToId(pojo.dt),
        temp = pojo.temp,
        feelsLike = pojo.feelsLike,
        pressure = pojo.pressure,
        humidity = pojo.humidity,
        clouds = pojo.clouds,
        windSpeed = pojo.windSpeed,
        windDeg = pojo.windDeg,
        weather = if (pojo.weather != null) {
            detailsToDbModel(pojo.weather[0])
        } else null
    )

    fun cityToModel(city: DataCity) = City(
        name = city.name ?: "",
        country = city.country ?: "",
        lat = city.lat ?: 0f,
        lon = city.lon ?: 0f,
        localNames = if (city.localNames != null) {
            localNamesModel(city.localNames)
        } else {
            null
        }
    )

    private fun tempToDb(temp: Temp) = TempDb(
        day = temp.day,
        night = temp.night
    )

    private fun detailsToDbModel(pojo: WeatherDetailsPojo) = WeatherDetailsDb(
        id = pojo.id,
        main = pojo.main,
        description = pojo.description,
        icon = pojo.icon
    )

    private fun dateToId(date: Long): Long {
        calendar.time = Date(date * 1000L)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        return (calendar.timeInMillis / 1000L)
    }

    private fun localNamesModel(localNames: LocalNamesData) = JSONObject(Gson().toJson(localNames))
//        LocalNames(
//        ru = localNames.ru,
//        en = localNames.en
//    )
}