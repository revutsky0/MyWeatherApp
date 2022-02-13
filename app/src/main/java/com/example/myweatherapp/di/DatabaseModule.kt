package com.example.myweatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.myweatherapp.data.database.AppDatabase
import com.example.myweatherapp.data.database.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

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

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase): WeatherDao {
        return appDatabase.dao()
    }
}