package com.example.myweatherapp.data.database

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
    suspend fun getCurrentWeather(): CurrentWeatherDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(weatherCurrent: CurrentWeatherDbModel)

    @Query("DELETE FROM weather_current")
    suspend fun deleteCurrentWeather()

    //endregion

    //region WEATHER_DAILY

    @Query("SELECT * FROM weather_daily ORDER BY dt")
    suspend fun getDailyWeatherList(): List<DailyWeatherDbModel>

    @Query("SELECT * FROM weather_daily WHERE dt==:dt")
    suspend fun getDailyWeatherByDt(dt: Long): DailyWeatherDbModel

    @Query("SELECT * FROM weather_daily WHERE dt==:dt")
    suspend fun getDailyWeatherByDtObj(dt: Long): DailyWeatherDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeather(daily: DailyWeatherDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeather(dailyList: List<DailyWeatherDbModel>)

    @Query("DELETE FROM weather_daily")
    suspend fun deleteDailyWeather()

    @Query("SELECT COUNT(*) FROM weather_daily")
    suspend fun getWeatherDailyCount(): Int

    //endregion

}