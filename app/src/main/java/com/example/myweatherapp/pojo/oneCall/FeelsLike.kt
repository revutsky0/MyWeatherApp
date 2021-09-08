package com.example.myweatherapp.pojo.oneCall

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class FeelsLike(
    @SerializedName("day")
    @Expose
    val day: Double? = null,

    @SerializedName("night")
    @Expose
    val night: Double? = null,

    @SerializedName("eve")
    @Expose
    val eve: Double? = null,

    @SerializedName("morn")
    @Expose
    val morn: Double? = null
)