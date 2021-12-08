package com.example.myweatherapp.presenter.main

import android.os.Bundle
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
        binding.etCity.setText(viewModel.cityName ?: "")
        viewModel.currentWeather.observe(this,
            {
                it?.let {
                    id = it.dt
                    with(binding) {
                        cvCurrentWeather.visibility = View.VISIBLE
                        tvCurrentTemp.text = it.getTemperature()
                        tvWeatherStatus.text = it.getWeatherStatus()
                        val background = it.getWeatherBackground()
                        if (currentBackground != background) {
                            MainConstraintLayout.background = resources.getDrawable(background)
                            currentBackground = background
                        }
                    }
                }
            }
        )
        with(binding) {
            rvWeeklyWeather.adapter = adapter
            rvWeeklyWeather.layoutManager
            viewModel.weeklyWeather.observe(this@MainActivity, {
                adapter.weeklyWeather = it
            })
            ibFindCity.setOnClickListener {
                viewModel.loadData(etCity.text.toString())
            }
            viewModel.currentDailyWeather.observe(this@MainActivity, {
                it?.let {
                    tvDayTemp.text = it.getDayTemp()
                    tvNightTemp.text = it.getNightTemp()
                }
            })
            clCurrentWeather.setOnClickListener {
                val intent = WeatherDetailActivity.getIntent(this@MainActivity, id)
                startActivity(intent)
            }
        }
    }
}