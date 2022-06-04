package com.example.myweatherapp.presenter.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.domain.usecase.city.LoadLastCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getLastCity: LoadLastCityUseCase
) : ViewModel() {

    private val delayDuration = 3000L
    val lastCity = MutableLiveData<City?>()

    fun load() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(delayDuration)
            val city = getLastCity()
            lastCity.postValue(city)
        }
    }


}