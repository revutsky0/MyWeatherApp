package com.example.myweatherapp.presenter.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.ActivityMainBinding
import com.example.myweatherapp.presenter.detail.WeatherDetailActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val adapter: WeeklyAdapter by lazy { WeeklyAdapter() }
    private var currentBackground = R.drawable.clouds_bg
    private var id = 0L

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setOnClickListeners()
        setObservable()
    }

    private fun setOnClickListeners() {
        with(binding) {
            rvWeeklyWeather.adapter = adapter
            ibFindCity.setOnClickListener {
                viewModel.findCity(etCity.text.toString())
            }
            clCurrentWeather.setOnClickListener {
                val intent = WeatherDetailActivity.getIntent(this@MainActivity, id)
                startActivity(intent)
            }
        }
    }

    private fun setObservable() {
        viewModel.weeklyWeather.observe(this@MainActivity, {
            Log.d("MAIN", "UPDATE WEEKLY WEATHER")
            adapter.weeklyWeather = it
        })
        viewModel.currentWeather.observe(this,
            {
                it?.let {
                    id = it.id
                    Log.d("MAIN", "UPDATE CURRENT WEATHER")
                    with(binding) {
                        cvCurrentWeather.visibility = View.VISIBLE
                        tvCurrentTemp.text = it.currentTemp
                        tvWeatherStatus.text = it.status
                        val background = it.background
                        if (currentBackground != background) {
                            MainConstraintLayout.background = resources.getDrawable(background)
                            currentBackground = background
                        }
                    }
                }
            }
        )
        viewModel.currentDailyWeather.observe(this@MainActivity, {
            it?.let {
                binding.tvDayTemp.text = it.dayTemp
                binding.tvNightTemp.text = it.nightTemp
            }
        })
    }
}