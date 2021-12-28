package com.example.myweatherapp.data.network.pojo.city

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

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

    @SerializedName("local_names")
    @Expose
    val localNames: LocalNamesData? = null
)