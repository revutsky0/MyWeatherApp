package com.example.myweatherapp.detailActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myweatherapp.database.WeatherDatabase

class WeatherDetailViewModel(application: Application,id : Int) : AndroidViewModel(application) {

    val database = WeatherDatabase.getInstance(application)

    init {

    }

}