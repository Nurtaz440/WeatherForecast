package com.nurtaz.dev.weatherapp.data.local.db.weather.inner

data class WeatherAttrEntity(
    var temp: Float?,
    val pressure: Int,
    val humidity: Int, var tempMin: Float, var tempMax: Float
) {
}