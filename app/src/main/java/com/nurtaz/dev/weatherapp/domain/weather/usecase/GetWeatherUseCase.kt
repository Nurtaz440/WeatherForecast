package com.nurtaz.dev.weatherapp.domain.weather.usecase

import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherCurrentRoomEntity
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.weather.repstry.WeatherRepository
import javax.inject.Inject

internal class GetWeatherUseCase @Inject constructor(private var weatherRepository: WeatherRepository)  {
    suspend operator fun invoke(): DomainResult<WeatherCurrentRoomEntity> = weatherRepository.getDetail()//DomainResult.Success(ModelWeather(0.1,0.2))//weatherRepository.getDetail()
}