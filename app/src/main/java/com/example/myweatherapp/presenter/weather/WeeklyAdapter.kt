package com.example.myweatherapp.presenter.weather

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.databinding.WeatherDailyItemBinding
import com.example.myweatherapp.domain.models.DailyWeatherListItem

class WeeklyAdapter : RecyclerView.Adapter<WeeklyAdapter.WeeklyWeatherViewHolder>() {

    var weeklyWeather: List<DailyWeatherListItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onWeatherDailyItemClickListener: OnWeatherDailyItemClickListener? = null

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

    fun interface OnWeatherDailyItemClickListener {
        fun onClick(id: Long)
    }

    inner class WeeklyWeatherViewHolder(val binding: WeatherDailyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var id: Long = 0

        init {
            itemView.setOnClickListener {
                onWeatherDailyItemClickListener?.onClick(id)
            }
        }
    }
}