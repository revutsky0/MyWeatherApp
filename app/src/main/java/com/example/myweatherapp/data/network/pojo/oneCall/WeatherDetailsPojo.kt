package com.example.myweatherapp.data.network.pojo.oneCall

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherDetailsPojo(
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

)