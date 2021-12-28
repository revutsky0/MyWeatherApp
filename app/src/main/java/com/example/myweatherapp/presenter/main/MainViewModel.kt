package com.example.myweatherapp.presenter.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myweatherapp.data.repository.CityRepositoryImpl
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.usecase.city.GetLastCityUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CityRepositoryImpl(application)
    private val getLastCityUseCase = GetLastCityUseCase(repository)

    fun getLastCity(): City? {
        var lastCity: City? = null
        CoroutineScope(Dispatchers.IO).launch {
            lastCity = getLastCityUseCase()
        }
        return lastCity
    }

}