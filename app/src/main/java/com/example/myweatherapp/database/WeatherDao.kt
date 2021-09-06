package com.example.myweatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myweatherapp.pojo.oneCall.WeatherCurrent
import com.example.myweatherapp.pojo.oneCall.WeatherDaily

@Dao
interface WeatherDao {

    //region WEATHER_CURRENT
    @Query("SELECT * FROM weather_current LIMIT 1")
    fun getCurrentWeather() : LiveData<WeatherCurrent>

    @Insert
    fun insertCurrentWeather(weatherCurrent: WeatherCurrent)

    @Query("DELETE FROM weather_current")
    fun deleteCurrentWeather()

    //endregion

    //region WEATHER_DAILY

    @Query("SELECT * FROM weather_daily")
    fun getDailyWeather() : LiveData<List<WeatherDaily>>

    @Query("SELECT * FROM weather_daily WHERE id==:id")
    fun getDailyWeatherById(id : Int) : LiveData<WeatherDaily>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(daily: WeatherDaily)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(dailyList: List<WeatherDaily>)

    @Query("DELETE FROM weather_daily")
    fun deleteDailyWeather()

    //endregion


}