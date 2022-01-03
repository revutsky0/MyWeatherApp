package com.example.myweatherapp.presenter.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.myweatherapp.data.repository.CityRepositoryImpl
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.usecase.city.LoadLastCityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CityRepositoryImpl(application)
    private val getLastCityUseCase = LoadLastCityUseCase(repository)

    fun getLastCity(): City? {
        var lastCity = getLastCityUseCase()
        Log.d("LAST", "Last city is $lastCity")
        return lastCity
    }

}