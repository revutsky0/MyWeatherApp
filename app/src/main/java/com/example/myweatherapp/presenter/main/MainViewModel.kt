package com.example.myweatherapp.presenter.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.data.repository.CityRepositoryImpl
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.CityRepository
import com.example.myweatherapp.domain.usecase.city.LoadLastCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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