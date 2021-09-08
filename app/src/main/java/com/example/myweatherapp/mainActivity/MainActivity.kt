package com.example.myweatherapp.mainActivity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.R
import com.example.myweatherapp.detailActivity.WeatherDetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val etCity: TextView by lazy { findViewById(R.id.etCity) }
    private val ibFindCity: ImageButton by lazy { findViewById(R.id.ibFindCity) }
    private val tvCurrentTemp: TextView by lazy { findViewById(R.id.tvCurrentTemp) }
    private val tvDayTemp: TextView by lazy { findViewById(R.id.tvDayTemp) }
    private val tvNightTemp: TextView by lazy { findViewById(R.id.tvNightTemp) }
    private val tvWeatherStatus: TextView by lazy { findViewById(R.id.tvWeatherStatus) }
    private val rvWeeklyWeather: RecyclerView by lazy { findViewById(R.id.rvWeeklyWeather) }
    private val adapter: WeeklyAdapter by lazy { WeeklyAdapter() }
    private val cardViewCurrentWeather: CardView by lazy { findViewById(R.id.cvCurrentWeather) }
    private val clCurrentWeather: ConstraintLayout by lazy { findViewById(R.id.clCurrentWeather) }
    private var id = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        etCity.text = viewModel.cityName ?: ""
        viewModel.currentWeather.observe(this,
            {
                it?.let {
                    id = it.dt
                    cardViewCurrentWeather.visibility = View.VISIBLE
                    tvCurrentTemp.text = it.getTemperature()
                    tvWeatherStatus.text = it.getWeatherStatus()
                }
            }
        )
        rvWeeklyWeather.adapter = adapter
        rvWeeklyWeather.layoutManager
        viewModel.weeklyWeather.observe(this, {
            adapter.weeklyWeather = it
        })
        ibFindCity.setOnClickListener {
            viewModel.loadData(etCity.text.toString())
        }
        viewModel.currentDailyWeather.observe(this, {
            it?.let {
                tvDayTemp.text = it.getDayTemp()
                tvNightTemp.text = it.getNightTemp()
            }
        })
        clCurrentWeather.setOnClickListener {
            val intent = WeatherDetailActivity.getIntent(this, id)
            startActivity(intent)
        }
    }
}