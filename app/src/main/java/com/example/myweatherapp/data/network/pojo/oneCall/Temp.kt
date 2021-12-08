package com.example.myweatherapp.data.network.pojo.oneCall

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class Temp(
    @SerializedName("day")
    @Expose
    val day: Float? = null,

    @SerializedName("min")
    @Expose
    val min: Float? = null,

    @SerializedName("max")
    @Expose
    val max: Float? = null,

    @SerializedName("night")
    @Expose
    val night: Float? = null,

    @SerializedName("eve")
    @Expose
    val eve: Float? = null,

    @SerializedName("morn")
    @Expose
    val morn: Float? = null

)