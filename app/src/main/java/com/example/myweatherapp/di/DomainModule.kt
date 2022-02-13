package com.example.myweatherapp.di

import com.example.myweatherapp.domain.repository.CityRepository
import com.example.myweatherapp.domain.repository.WeatherRepository
import com.example.myweatherapp.domain.usecase.city.GetCityListUseCase
import com.example.myweatherapp.domain.usecase.city.GetCityWeatherUseCase
import com.example.myweatherapp.domain.usecase.city.LoadLastCityUseCase
import com.example.myweatherapp.domain.usecase.city.SaveLastCityUseCase
import com.example.myweatherapp.domain.usecase.weather.GetCurrentWeatherUseCase
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherListUseCase
import com.example.myweatherapp.domain.usecase.weather.GetDailyWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {



    @Provides
    fun provideGetCityListUseCase(repository: CityRepository): GetCityListUseCase {
        return GetCityListUseCase(repository)
    }

    @Provides
    fun provideGetCityWeatherUseCase(repository: WeatherRepository): GetCityWeatherUseCase {
        return GetCityWeatherUseCase(repository)
    }

    @Provides
    fun provideLoadLastCityUseCase(repository: CityRepository): LoadLastCityUseCase {
        return LoadLastCityUseCase(repository)
    }

    @Provides
    fun provideSaveLastCityUseCase(repository: CityRepository): SaveLastCityUseCase {
        return SaveLastCityUseCase(repository)
    }

    @Provides
    fun provideGetCurrentWeatherUseCase(repository: WeatherRepository): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(repository)
    }

    @Provides
    fun provideGetDailyWeatherListUseCase(repository: WeatherRepository): GetDailyWeatherListUseCase {
        return GetDailyWeatherListUseCase(repository)
    }

    @Provides
    fun provideGetDailyWeatherUseCase(repository: WeatherRepository): GetDailyWeatherUseCase {
        return GetDailyWeatherUseCase(repository)
    }
}