package com.example.myweatherapp.data.workers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.myweatherapp.data.database.AppDatabase
import com.example.myweatherapp.data.mappers.NetworkMapper
import com.example.myweatherapp.data.network.api.ApiFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltWorker
class LoadWeatherWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted params: WorkerParameters,
    private val database: AppDatabase,
    private val mapper: NetworkMapper) :
    CoroutineWorker(
        appContext,
        params
    ) {

    companion object {

        const val NAME = "WEATHER_LOADER"
        private const val LAT_PARAM = "lat"
        private const val LON_PARAM = "lon"
        private const val DELAY_PARAM = "delay"

        fun getWorkRequest(delay: Long, lan: Float, lot: Float) =
            OneTimeWorkRequestBuilder<LoadWeatherWorker>()
                .setInputData(
                    workDataOf(
                        DELAY_PARAM to delay,
                        LAT_PARAM to lan,
                        LON_PARAM to lot
                    ),
                )
                .build()
    }

//    @Inject
//    lateinit var database: AppDatabase
//
//    @Inject
//    lateinit var mapper: NetworkMapper
//    private val mapper = NetworkMapper()

    private val connectivityManager =
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override suspend fun doWork(): Result {
        Log.d("MAIN", "WORKER IS RUN")
        val lat = inputData.getFloat(LAT_PARAM, 0f)
        val lon = inputData.getFloat(LON_PARAM, 0f)
        val delay = inputData.getLong(DELAY_PARAM, 5000)
        while (true) {
            if (!isInternetAvailable()) {
                break
            }
            val data = ApiFactory.apiService.getWeather(lat, lon)
            val daily = data.daily?.map { mapper.dailyToDbModel(it) }
            val current = if (data.current != null) {
                mapper.currentToDbModel(data.current)
            } else null
            daily?.let { database.dao().insertDailyWeather(daily) }
            current?.let { database.dao().insertCurrentWeather(current) }
            Log.d("MAIN", "WORKER ITERATION IS COMPLETE")
            delay(delay)
        }
        return Result.success()
    }

    private fun isInternetAvailable() = true
//    : Boolean {
//        val networkCapabilities = connectivityManager.activeNetwork ?: return false
//        val actNw =
//            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
//        return when {
//            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//            else -> false
//        }
//    }

}