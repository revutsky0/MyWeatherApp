package com.example.myweatherapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myweatherapp.data.database.dbmodels.CurrentWeatherDbModel
import com.example.myweatherapp.data.database.dbmodels.DailyWeatherDbModel

@Dao
interface WeatherDao {

    //region WEATHER_CURRENT

    @Query("SELECT * FROM weather_current ORDER BY dt DESC LIMIT 1")
    fun getCurrentWeather(): LiveData<CurrentWeatherDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(weatherCurrent: CurrentWeatherDbModel)

    @Query("DELETE FROM weather_current")
    suspend fun deleteCurrentWeather()

    @Query("DELETE FROM weather_daily WHERE dt < :dt ")
    suspend fun deleteOldCurrent(dt: Long)

    //endregion

    //region WEATHER_DAILY

    @Query("SELECT * FROM weather_daily ORDER BY dt")
    fun getDailyWeatherList(): LiveData<List<DailyWeatherDbModel>>

    @Transaction
    @Query("SELECT * FROM weather_daily WHERE dt==(SELECT dt FROM weather_current ORDER BY dt DESC LIMIT 1)")
    fun getDailyWeather(): LiveData<DailyWeatherDbModel>

    @Query("SELECT * FROM weather_daily WHERE dt == :dt")
    fun getDailyWeatherByDt(dt: Long): LiveData<DailyWeatherDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeather(daily: DailyWeatherDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyWeather(dailyList: List<DailyWeatherDbModel>)

    @Query("DELETE FROM weather_daily")
    suspend fun deleteDailyWeather()

    @Query("DELETE FROM weather_daily WHERE dt < :dt")
    suspend fun deleteOldDaily(dt: Long)

    //endregion

}