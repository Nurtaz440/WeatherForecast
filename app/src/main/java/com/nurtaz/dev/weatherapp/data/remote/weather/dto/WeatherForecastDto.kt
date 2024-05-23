package com.nurtaz.dev.weatherapp.data.remote.weather.dto

import com.nurtaz.dev.weatherapp.data.remote.weather.dto.inner.WeatherForecastDayDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecastDto(
//    val city: WeatherCityDto,
    val cnt: Int,
    val list: List<WeatherForecastDayDto>) {

}