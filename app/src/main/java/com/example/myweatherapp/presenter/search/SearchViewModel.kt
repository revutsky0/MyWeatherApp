package com.example.myweatherapp.presenter.search

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.usecase.city.GetCityFromLocationUseCase
import com.example.myweatherapp.domain.usecase.city.GetCityListUseCase
import com.example.myweatherapp.domain.usecase.city.LoadLastCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCityList: GetCityListUseCase,
    private val getCityListByLocation: GetCityFromLocationUseCase,
    private val getLastCityUseCase: LoadLastCityUseCase
) : ViewModel() {

    private val _cityNotFound = MutableLiveData<Any>()
    val cityNotFound: LiveData<Any> = _cityNotFound

    private val _cityList = MutableLiveData<List<City>>()
    val cityList: LiveData<List<City>> = _cityList

    fun needToGetLocationOnStartFragment() = getLastCityUseCase()==null

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

    fun searchCity(location: Location) {
        CoroutineScope(Dispatchers.IO).launch {
            val cityList = getCityListByLocation(location)
            if (cityList.isEmpty()) {
                _cityNotFound.postValue(Any())
            } else {
                _cityList.postValue(cityList)
            }
        }
    }

}