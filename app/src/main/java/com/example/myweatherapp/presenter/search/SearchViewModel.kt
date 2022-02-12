package com.example.myweatherapp.presenter.search

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.data.repository.CityRepositoryImpl
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.repository.CityRepository
import com.example.myweatherapp.domain.usecase.city.GetCityListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCityList: GetCityListUseCase
) : ViewModel() {

    private val _cityNotFound = MutableLiveData<Any>()
    val cityNotFound: LiveData<Any> = _cityNotFound

    private val _cityList = MutableLiveData<List<City>>()
    val cityList: LiveData<List<City>> = _cityList

    fun searchCity(cityName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val cityList = getCityList(cityName)
            if (cityList.isEmpty()) {
                _cityNotFound.postValue(Any())
            } else {
                _cityList.postValue(cityList)
            }
        }
    }

}