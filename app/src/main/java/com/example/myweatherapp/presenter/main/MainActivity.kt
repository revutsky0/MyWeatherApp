package com.example.myweatherapp.presenter.main

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.presenter.search.SearchCityFragment
import com.example.myweatherapp.presenter.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val manager: LocationManager by lazy { getSystemService(LOCATION_SERVICE) as LocationManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!checkPermission()) {
            requestPermissions()
        }
        val lastCity = viewModel.getLastCity()
        if (lastCity == null) {
            launchSearchFragment()
        } else {
            launchWeatherActivity(lastCity)
        }
    }

    private fun checkPermission() =
        (ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_PERMISSIONS_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {

            } else {

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun launchWeatherActivity(city: City) = supportFragmentManager.beginTransaction()
        .add(R.id.mainActivityFCV, WeatherFragment.newInstance(city))
        .commit()

    private fun launchSearchFragment() = supportFragmentManager.beginTransaction()
        .add(R.id.mainActivityFCV, SearchCityFragment.newInstance())
        .commit()

    companion object {
        private const val REQUEST_PERMISSIONS_CODE = 123321
    }

}