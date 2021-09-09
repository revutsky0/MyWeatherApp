package com.example.myweatherapp.mainActivity

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.myweatherapp.api.ApiFactory
import com.example.myweatherapp.database.WeatherDatabase
import com.example.myweatherapp.getDateWithNullTime
import com.example.myweatherapp.pojo.oneCall.WeatherDaily
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val PREFERENCES_CITY_NAME = "CityName"
        const val PREFERENCES_CITY_LAT = "CityLat"
        const val PREFERENCES_CITY_LON = "CityLon"
        const val GET_DATE_PERIOD_VALUE = 60L
        val GET_DATE_PERIOD_UNITS = TimeUnit.SECONDS
    }

    private val database = WeatherDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    private var disposableCity: Disposable? = null
    private var disposableData: Disposable? = null
    val currentWeather = database.dao().getCurrentWeather()
    val currentDailyWeather = MutableLiveData<WeatherDaily>()
    val weeklyWeather = database.dao().getDailyWeather()
    private val preferences =
        application.getSharedPreferences("WeatherSettings", AppCompatActivity.MODE_PRIVATE)
    var cityName: String?
    private var lat: Double? = null
    private var lon: Double? = null

    init {
        with(preferences) {
            cityName = getString("CityName", null)
            if (contains(PREFERENCES_CITY_LAT)) {
                lat = getLong(PREFERENCES_CITY_LAT, 0).toDouble()
            }
            if (contains(PREFERENCES_CITY_LON)) {
                lon = getLong(PREFERENCES_CITY_LAT, 0).toDouble()
            }

        }
        if (cityName != null && lat != null && lon != null) {
            getData(lat, lon)
        }
    }

    private fun getData(lat: Double?, lon: Double?) {
        if (lat == null || lon == null) return
        disposableData?.dispose()
        disposableData = Observable.interval(0, GET_DATE_PERIOD_VALUE, GET_DATE_PERIOD_UNITS)
            .flatMap {
                ApiFactory.apiService.getWeather(lat, lon)
            }
            .retry()
            .repeat()
            .subscribeOn(Schedulers.io())
            .subscribe({
                val count = database.dao().getWeatherDailyCount()
                if (count>7) {
                    database.dao().deleteCurrentWeather()
                    database.dao().deleteDailyWeather()
                }
                it.daily?.let {
                    it.map {
                        it.dt = getDateWithNullTime(it.dt)
                    }
                    database.dao().insertDailyWeather(it)
                }
                it.current?.let {
                    it.dt = getDateWithNullTime(it.dt)
                    database.dao().insertCurrentWeather(it)
                    currentDailyWeather.postValue(database.dao().getDailyWeatherByDtObj(it.dt))

                }
                Log.d("MyApp", "Message = $it")
            }, {
                Log.d("MyApp", "subscribe error = ${it.message}")
            }
            )
        compositeDisposable.add(disposableData)
    }

    fun loadData(cityName: String) {
        disposableData?.dispose()
        disposableCity = ApiFactory.apiService
            .getDataOfCity(cityName)
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.isNotEmpty()) {
                    val city = it[0]
                    Log.d("MyApp", "city = $it")
                    if (city.lat != null && city.lon != null) {
                        preferences.edit()
                            .putString(PREFERENCES_CITY_NAME, city.name)
                            .putLong(PREFERENCES_CITY_LAT, city.lat.toLong())
                            .putLong(PREFERENCES_CITY_LON, city.lon.toLong())
                            .apply()
                        getData(city.lat, city.lon)
                    }
                } else {
                    Log.d("MyApp", "city = null")

                }

            }, {

                Log.d("MyApp", "find city error : ${it.message}")
            })
        compositeDisposable.add(disposableCity)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

