package com.nurtaz.dev.weatherapp.data.remote.weather

import com.nurtaz.dev.weatherapp.data.base.remote.handleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteWeatherSource @Inject constructor(
    var apiWeather: ApiWeather
){

    suspend fun  getCurrent(lat: Float, lon: Float) = withContext(Dispatchers.IO) {

        return@withContext handleApi { apiWeather.getCurrent(lat, lon) }


    }

    suspend fun  getForecast(lat: Float, lon: Float) = withContext(Dispatchers.IO) {

        return@withContext handleApi { apiWeather.getForecast(lat, lon) }


    }
}