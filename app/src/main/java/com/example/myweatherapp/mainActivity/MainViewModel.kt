package com.example.myweatherapp.mainActivity

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import com.example.myweatherapp.api.ApiFactory
import com.example.myweatherapp.database.WeatherDatabase
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
    }

    val database = WeatherDatabase.getInstance(application)
    val compositeDisposable = CompositeDisposable()
    var disposableCity: Disposable? = null
    var disposableData: Disposable? = null
    val currentWeather = database.dao().getCurrentWeather()
    val weeklyWeather = database.dao().getDailyWeather()
    val preferences =
        application.getSharedPreferences("WeatherSettings", AppCompatActivity.MODE_PRIVATE)
    var cityName: String?
    var lat: Double? = null
    var lon: Double? = null

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
            Log.d("MyApp","viewModel initialize")
            getData(lat, lon)
        }
    }

    fun getData(lat: Double?, lon: Double?) {
        if (lat == null || lon == null) return
        disposableData?.dispose()
        disposableData = Observable.interval(0, 5, TimeUnit.SECONDS)
            .flatMap {
                ApiFactory.apiService.getWeather(lat, lon)
            }
            .retry()
            .repeat()
            .subscribeOn(Schedulers.io())
            .subscribe({
                it.current?.let {
                    //database.dao().deleteCurrentWeather()
                    database.dao().insertCurrentWeather(it)
                }
                it.daily?.let {
                    //database.dao().deleteDailyWeather()
                    database.dao().insertDailyWeather(it)
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
                    Log.d("MyApp","city = $it")
                    if (city.lat != null && city.lon != null) {
                        preferences.edit()
                            .putString(PREFERENCES_CITY_NAME,city.name)
                            .putLong(PREFERENCES_CITY_LAT,city.lat.toLong())
                            .putLong(PREFERENCES_CITY_LON,city.lon.toLong())
                            .apply()
                        getData(city.lat, city.lon)
                    }
                } else {
                    Log.d("MyApp","city = null")

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

