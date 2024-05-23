package com.nurtaz.dev.weatherapp.data.remote.weather.dto.inner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherLocationDto(val lon: Float, val lat: Float) {
}