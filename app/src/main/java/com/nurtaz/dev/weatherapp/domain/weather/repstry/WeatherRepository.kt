package com.nurtaz.dev.weatherapp.domain.weather.repstry

import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherCurrentRoomEntity
import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherForecastRoomData
import com.nurtaz.dev.weatherapp.domain.base.DomainResult

interface WeatherRepository {

    suspend fun getDetail(): DomainResult<WeatherCurrentRoomEntity>
    suspend fun getForecast(): DomainResult<WeatherForecastRoomData>

    suspend fun clearData(): DomainResult<Unit>
}