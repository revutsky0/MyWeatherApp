package com.example.myweatherapp.presenter.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.WeatherDailyItemBinding
import com.example.myweatherapp.domain.models.DailyWeatherListItem
import com.example.myweatherapp.presenter.detail.WeatherDetailActivity

class WeeklyAdapter : RecyclerView.Adapter<WeeklyAdapter.WeeklyWeatherViewHolder>() {

    var weeklyWeather: List<DailyWeatherListItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyWeatherViewHolder {
        val view =
            WeatherDailyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeeklyWeatherViewHolder(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: WeeklyWeatherViewHolder, position: Int) {
        val item = weeklyWeather[position]
        holder.id = item.id
        with(holder.binding) {
            tvItemDate.text = item.date
            tvItemDayNightTemp.text = "${item.dayTemp} / ${item.nightTemp}"
            tvItemDayOfWeek.text = item.dayOfWeek
            tvItemWindSpeed.text = "${item.windSpeed}"
            ivWeatherIcon.setImageDrawable(holder.itemView.resources.getDrawable(item.icon))
            ivWeatherIcon.contentDescription = item.status
        }
    }

    override fun getItemCount(): Int {
        return weeklyWeather.size
    }

    class WeeklyWeatherViewHolder(val binding: WeatherDailyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var id: Long = 0

        init {
            itemView.setOnClickListener {
                val intent = WeatherDetailActivity.getIntent(itemView.context, id)
                itemView.context.startActivity(intent)
            }
        }
    }
}