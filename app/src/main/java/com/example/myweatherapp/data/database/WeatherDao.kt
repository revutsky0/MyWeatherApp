package com.example.myweatherapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweatherapp.data.database.dbmodels.CurrentWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.DailyWeatherDbModel

@Dao
interface WeatherDao {

    //region WEATHER_CURRENT

    @Query("SELECT * FROM weather_current LIMIT 1")
    fun getCurrentWeather(): CurrentWeatherDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(weatherCurrent: CurrentWeatherDbModel)

    @Query("DELETE FROM weather_current")
    fun deleteCurrentWeather()

    //endregion

    //region WEATHER_DAILY

    @Query("SELECT * FROM weather_daily ORDER BY dt")
    fun getDailyWeather(): LiveData<List<DailyWeatherDbModel>>

    @Query("SELECT * FROM weather_daily WHERE dt==:dt")
    fun getDailyWeatherByDt(dt: Long): DailyWeatherDbModel

    @Query("SELECT * FROM weather_daily WHERE dt==:dt")
    fun getDailyWeatherByDtObj(dt: Long): DailyWeatherDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(daily: DailyWeatherDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(dailyList: List<DailyWeatherDbModel>)

    @Query("DELETE FROM weather_daily")
    fun deleteDailyWeather()


    @Query("SELECT COUNT(*) FROM weather_daily")
    fun getWeatherDailyCount() : Int

    //endregion


}