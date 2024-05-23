package com.nurtaz.dev.weatherapp.data.remote.weather.dto.inner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherMainDto(val temp: Float,
                          val pressure: Int,
                          val humidity: Int,
                          @Json(name = "temp_min") val tempMin: Float,
                          @Json(name = "temp_max") val tempMax: Float,) {
}