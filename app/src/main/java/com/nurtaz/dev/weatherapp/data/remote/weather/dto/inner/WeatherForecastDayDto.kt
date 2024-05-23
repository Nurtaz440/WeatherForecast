package com.nurtaz.dev.weatherapp.data.remote.weather.dto.inner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecastDayDto(val dt: Int, val main: WeatherDayTempDto,
                                 val weather: List<WeatherDto>) {
}