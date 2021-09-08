package com.example.myweatherapp.detailActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.pojo.oneCall.WeatherDaily

class WeatherDetailActivity : AppCompatActivity() {

    companion object {
        const val INTENT_ID_NAME = "WeatherId"
        fun getIntent(context: Context, id: Long): Intent {
            val intent = Intent(context, WeatherDetailActivity::class.java)
            intent.putExtra(INTENT_ID_NAME, id)
            return intent
        }
    }

    private val tvDayAndDate: TextView by lazy { findViewById(R.id.tvDetailsDayAndDate) }
    private val tvDayNightTemp: TextView by lazy { findViewById(R.id.tvDetailsDayNightTemp) }
    private val tvStatus: TextView by lazy { findViewById(R.id.tvDetailsStatus) }
    private val tvClouds: TextView by lazy { findViewById(R.id.tvDetailsClouds) }
    private val tvHumidity: TextView by lazy { findViewById(R.id.tvDetailsHumidity) }
    private val tvPrecipitation: TextView by lazy { findViewById(R.id.tvDetailsPrecipitation) }
    private val tvWind: TextView by lazy { findViewById(R.id.tvDetailsWind) }
    private val tvPressure: TextView by lazy { findViewById(R.id.tvDetailsPressure) }

    private lateinit var viewModel: WeatherDetailViewModel
    private lateinit var weather: LiveData<WeatherDaily>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)
        val id = intent.getLongExtra(INTENT_ID_NAME, 0)
        viewModel = ViewModelProvider(this)[WeatherDetailViewModel::class.java]
        weather = viewModel.getWeather(id)
        weather.observe(this, {
            it?.let {
                tvDayAndDate.text = it.getDayAndDate()
                tvDayNightTemp.text = it.getDayNightTemp()
                tvStatus.text = it.getWeatherDescription()
                tvClouds.text = it.getCloudsValue()
                tvHumidity.text = it.getHumidityValue()
                tvPrecipitation.text = it.getPrecipitation()
                tvWind.text = it.getWind()
                tvPressure.text = it.getPressures()
            }
        })

    }
}