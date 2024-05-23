package com.nurtaz.dev.weatherapp.data.remote.weather.dto

import com.nurtaz.dev.weatherapp.data.remote.weather.dto.inner.WeatherDto
import com.nurtaz.dev.weatherapp.data.remote.weather.dto.inner.WeatherLocationDto
import com.nurtaz.dev.weatherapp.data.remote.weather.dto.inner.WeatherMainDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherCurrentDto(
    val id: Int,
    val coord: WeatherLocationDto,
    val weather: List<WeatherDto>,
    val main: WeatherMainDto,
    val visibility: Int,
    val dt: Int
) {
}