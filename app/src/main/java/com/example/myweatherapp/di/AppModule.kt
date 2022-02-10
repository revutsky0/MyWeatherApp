package com.example.myweatherapp.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.example.myweatherapp.data.database.AppDatabase
import com.example.myweatherapp.data.mappers.NetworkMapper
import com.example.myweatherapp.data.mappers.WeatherMapper
import com.example.myweatherapp.data.repository.CityRepositoryImpl
import com.example.myweatherapp.data.repository.WeatherRepositoryImpl
import com.example.myweatherapp.domain.repository.CityRepository
import com.example.myweatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherDatabase(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "WeatherDatabase"
        ).build()
    }

    @Provides
    fun provideWeatherDao(database: AppDatabase) = database.dao()

    @Provides
    fun provideWeatherRepository(
        @ApplicationContext context: Context,
        database: AppDatabase,
        mapper: WeatherMapper,
        manager: WorkManager
    ): WeatherRepository {
        return WeatherRepositoryImpl(context, database, mapper, manager)
    }

    @Provides
    fun provideCityRepository(
        @ApplicationContext context: Context,
        mapper: NetworkMapper
    ): CityRepository {
        return CityRepositoryImpl(context, mapper)
    }

    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }
}