package com.example.myweatherapp.presenter.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.usecase.city.LoadLastCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLastCityUseCase: LoadLastCityUseCase
) : ViewModel() {

    fun getLastCity(): City? {
        val lastCity = getLastCityUseCase()
        Log.d("LAST", "Last city is $lastCity")
        return lastCity
    }

}