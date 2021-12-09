package com.example.myweatherapp.data.network.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class DataCity(
    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("lat")
    @Expose
    val lat: Float? = null,

    @SerializedName("lon")
    @Expose
    val lon: Float? = null,

    @SerializedName("country")
    @Expose
    val country: String? = null,

    @SerializedName("state")
    @Expose
    val state: String? = null
)