package com.example.myweatherapp.presenter.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myweatherapp.data.repository.CityRepositoryImpl
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.usecase.city.GetCityListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CityRepositoryImpl(application)
    private val getCityList = GetCityListUseCase(repository)

    private val _cityList = MutableLiveData<List<City>>()
    val cityList: LiveData<List<City>> = _cityList

    fun searchCity(cityName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val cityList = getCityList(cityName)
            _cityList.postValue(cityList)
        }
    }

}