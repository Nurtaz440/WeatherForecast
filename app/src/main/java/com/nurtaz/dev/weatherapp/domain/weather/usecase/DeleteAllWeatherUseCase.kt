package com.nurtaz.dev.weatherapp.domain.weather.usecase

import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.weather.repstry.WeatherRepository
import javax.inject.Inject

class DeleteAllWeatherUseCase  @Inject constructor(private var weatherRepository: WeatherRepository)  {

    suspend operator fun invoke( ): DomainResult<Unit> = weatherRepository.clearData()
}