package com.example.myweatherapp.domain.models

import org.json.JSONObject
import java.io.Serializable
import java.util.*

data class City(
    val name: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val localNames: JSONObject? = null
) : Serializable {

    fun getLocalName(): String {
        if (localNames == null) return name
        return try {
            localNames.getString(Locale.getDefault().language)
        } catch (e: Exception) {
            name
        }
    }

    override fun toString(): String {
        return "${getLocalName()}, $country"
    }
}