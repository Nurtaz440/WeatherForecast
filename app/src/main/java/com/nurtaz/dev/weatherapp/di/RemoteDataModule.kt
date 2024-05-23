package com.nurtaz.dev.weatherapp.di

import com.nurtaz.dev.weatherapp.domain.identify.imple.DefaultIdentifyRepository
import com.nurtaz.dev.weatherapp.domain.identify.repository.IdentifyRepository
import com.nurtaz.dev.weatherapp.domain.plant.impl.DefaultPlantRepository
import com.nurtaz.dev.weatherapp.domain.plant.repository.PlantRepository
import com.nurtaz.dev.weatherapp.domain.weather.impl.DefaultWeatherRepository
import com.nurtaz.dev.weatherapp.domain.weather.repstry.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDataModule {
    @Binds
    abstract fun bindWeatherRepository(impl: DefaultWeatherRepository): WeatherRepository
    @Binds
    abstract fun bindPlantRepository(impl: DefaultPlantRepository): PlantRepository

    @Binds
    abstract fun bindIdentifyRepository(impl: DefaultIdentifyRepository): IdentifyRepository

}