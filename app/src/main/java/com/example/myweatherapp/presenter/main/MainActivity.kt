package com.example.myweatherapp.presenter.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.presenter.search.SearchCityFragment
import com.example.myweatherapp.presenter.weather.WeatherFragment

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lastCity = viewModel.getLastCity()
        if (lastCity == null) {
            launchSearchFragment()
        } else {
            launchWeatherActivity(lastCity)
        }
    }

    private fun launchWeatherActivity(city: City) = supportFragmentManager.beginTransaction()
        .add(R.id.mainActivityFCV, WeatherFragment.newInstance(city))
        .commit()

    private fun launchSearchFragment() = supportFragmentManager.beginTransaction()
        .add(R.id.mainActivityFCV, SearchCityFragment.newInstance())
        .commit()
}