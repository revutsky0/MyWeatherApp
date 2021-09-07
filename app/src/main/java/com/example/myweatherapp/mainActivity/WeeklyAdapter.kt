package com.example.myweatherapp.mainActivity

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myweatherapp.R
import com.example.myweatherapp.detailActivity.WeatherDetailActivity
import com.example.myweatherapp.pojo.oneCall.WeatherDaily

class WeeklyAdapter : RecyclerView.Adapter<WeeklyAdapter.WeeklyWeatherViewHolder>() {

    var weeklyWeather: List<WeatherDaily> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeklyWeatherViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.weather_daily_item, parent, false)
        return WeeklyWeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeeklyWeatherViewHolder, position: Int) {
        val item = weeklyWeather[position]
        holder.id = item.id
        holder.tvDate.text = item.getDate()
        holder.tvDayNightTemp.text = item.getDayNightTemp()
        holder.tvDayOfWeek.text = item.getDayOfWeek()
        holder.tvWingSpeed.text = item.windSpeed?.toString()
    }

    override fun getItemCount(): Int {
        return weeklyWeather.size
    }

    class WeeklyWeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id : Int = 0;
        val tvDayNightTemp: TextView by lazy { itemView.findViewById(R.id.tvItemDayNightTemp) }
        val tvDate: TextView by lazy { itemView.findViewById(R.id.tvItemDate) }
        val tvDayOfWeek: TextView by lazy { itemView.findViewById(R.id.tvItemDayOfWeek) }
        val tvWingSpeed: TextView by lazy { itemView.findViewById(R.id.tvItemWindSpeed) }
        init {
            itemView.setOnClickListener{
                Log.d("MyApp","id = $id")
                val intent = WeatherDetailActivity.getIntent(itemView.context,id)
                itemView.context.startActivity(intent)
            }
        }
    }
}