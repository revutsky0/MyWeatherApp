package com.example.myweatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.pojo.oneCall.WeatherCurrent
import com.example.myweatherapp.pojo.oneCall.WeatherDaily

@Dao
interface WeatherDao {

    //region WEATHER_CURRENT

    @Query("SELECT * FROM weather_current LIMIT 1")
    fun getCurrentWeather(): LiveData<WeatherCurrent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(weatherCurrent: WeatherCurrent)

    @Query("DELETE FROM weather_current")
    fun deleteCurrentWeather()

    //endregion

    //region WEATHER_DAILY

    @Query("SELECT * FROM weather_daily ORDER BY dt")
    fun getDailyWeather(): LiveData<List<WeatherDaily>>

    @Query("SELECT * FROM weather_daily WHERE dt==:dt")
    fun getDailyWeatherByDt(dt: Long): LiveData<WeatherDaily>

    @Query("SELECT * FROM weather_daily WHERE dt==:dt")
    fun getDailyWeatherByDtObj(dt: Long): WeatherDaily

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(daily: WeatherDaily)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(dailyList: List<WeatherDaily>)

    @Query("DELETE FROM weather_daily")
    fun deleteDailyWeather()

    //endregion


}