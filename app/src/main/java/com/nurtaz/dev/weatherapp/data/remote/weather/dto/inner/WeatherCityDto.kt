package com.nurtaz.dev.weatherapp.data.remote.weather.dto.inner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherCityDto(val id: Int, val name: String,val timeZone: Int,
                          val coord: WeatherLocationDto) {
}