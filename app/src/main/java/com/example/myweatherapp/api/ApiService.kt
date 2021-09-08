package com.example.myweatherapp.api

import com.example.myweatherapp.pojo.DataCity
import com.example.myweatherapp.pojo.oneCall.DataOneCall
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {

        const val API_KEY = "ba87b3a9b23c4aa69485d96061abe59b"

        // ENDPOINTS
        private const val URL_DATA = "data/2.5/"
        const val ENDPOINT_ONE_CALL = URL_DATA + "onecall"
        const val ENDPOINT_CURRENT = URL_DATA + "weather"
        const val ENDPOINT_COORDINATES = "geo/1.0/direct"

        // PARAMS NAME
        const val PARAM_NAME_API_KEY = "appid"
        const val PARAM_NAME_CITY = "q"
        const val PARAM_NAME_LATITUDE = "lat"
        const val PARAM_NAME_LONGITUDE = "lon"
        const val PARAM_NAME_EXCLUDE = "exclude"
        const val PARAM_NAME_UNITS = "units"
        const val PARAM_NAME_LANGUAGE = "lang"

        // PARAMS
        const val PARAM_UNITS_STANDARD = "standard"
        const val PARAM_UNITS_METRIC = "metric"
        const val PARAM_UNITS_IMPERIAL = "imperial"

        const val PARAM_EXCLUDE_CURRENT = "current"
        const val PARAM_EXCLUDE_ALERTS = "alerts"
        const val PARAM_EXCLUDE_MINUTELY = "minutely"
        const val PARAM_EXCLUDE_HOURLY = "hourly"
        const val PARAM_EXCLUDE_DAILY = "daily"

        const val PARAM_LANGUAGE_RUSSIAN = "ru"
        const val PARAM_LANGUAGE_ENGLISH = "en"

    }

    @GET(ENDPOINT_COORDINATES)
    fun getDataOfCity(
        @Query(PARAM_NAME_CITY) cityName: String,
        @Query(PARAM_NAME_API_KEY) apiKey: String = API_KEY,
    ): Single<List<DataCity>>

    @GET(ENDPOINT_ONE_CALL)
    fun getWeather(
        @Query(PARAM_NAME_LATITUDE) lat: Double,
        @Query(PARAM_NAME_LONGITUDE) lon: Double,
        @Query(PARAM_NAME_EXCLUDE) exclude: String = "$PARAM_EXCLUDE_ALERTS,$PARAM_EXCLUDE_MINUTELY,$PARAM_EXCLUDE_HOURLY",
        @Query(PARAM_NAME_LANGUAGE) lang: String = PARAM_LANGUAGE_RUSSIAN,
        @Query(PARAM_NAME_UNITS) units: String = PARAM_UNITS_METRIC,
        @Query(PARAM_NAME_API_KEY) apiKey: String = API_KEY
    ): Observable<DataOneCall>

}