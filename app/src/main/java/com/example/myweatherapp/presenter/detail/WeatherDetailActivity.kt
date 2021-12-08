package com.example.myweatherapp.presenter.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.ActivityWeatherDetailBinding

class WeatherDetailActivity : AppCompatActivity() {

    companion object {

        private const val INTENT_ID_NAME = "WeatherId"

        fun getIntent(context: Context, id: Long): Intent {
            val intent = Intent(context, WeatherDetailActivity::class.java)
            intent.putExtra(INTENT_ID_NAME, id)
            return intent
        }
    }

    private val binding by lazy { ActivityWeatherDetailBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[WeatherDetailViewModel::class.java] }
    private val pressureUnits by lazy { this.getString(R.string.wind_speed_units) }
    private val windUnits by lazy { this.getString(R.string.pressure_units) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getLongExtra(INTENT_ID_NAME, 0)
        viewModel.getWeather(id)
        viewModel.weather.observe(this, {
            it?.let {
                with(binding) {
                    tvDetailsDayAndDate.text = "${it.dayOfWeek}, ${it.date}"
                    tvDetailsDayNightTemp.text =
                        "${it.dayTemp} / ${it.nightTemp}"
                    tvDetailsStatus.text = it.status
                    tvDetailsClouds.text = "${it.clouds}%"
                    tvDetailsHumidity.text = "${it.humidity}%"
                    tvDetailsPrecipitation.text = "${it.precipitation}%"
                    tvDetailsWind.text = "${it.wind} $windUnits"
                    tvDetailsPressure.text = "${it.pressure} $pressureUnits"
                }
            }
        })
    }
}