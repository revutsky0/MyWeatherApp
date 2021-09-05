package com.example.myweatherapp.pojo.oneCall

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class WeatherDetails(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

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