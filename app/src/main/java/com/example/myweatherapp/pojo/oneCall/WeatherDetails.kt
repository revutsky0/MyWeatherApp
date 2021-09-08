package com.example.myweatherapp.pojo.oneCall

import android.util.Log
import com.example.myweatherapp.R
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class WeatherDetails(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("main")
    @Expose
    val main: String? = null,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("icon")
    @Expose
    val icon: String? = null

) {
    fun getIcon() = when(id) {
        800 -> R.drawable.sunny
        in 801..804 -> R.drawable.cloud
        in 600..699 -> R.drawable.show
        in 300..599 -> R.drawable.rainy
        in 200..299 -> R.drawable.thunder
        else ->  R.drawable.sunny
    }

    fun getBackground() : Int {
        Log.d("MyApp","bg id =  $id")
        return when(id) {
            800 -> R.drawable.sunny_bg
            801,302 -> R.drawable.partly_cloudy_bg
            803,804 -> R.drawable.clouds_bg
            in 600..699 -> R.drawable.snowfall_bg
            in 300..599 -> R.drawable.rain_bg
            in 200..299 -> R.drawable.thunderstorm_bg
            else ->  R.drawable.sunny_bg
        }
    }
}