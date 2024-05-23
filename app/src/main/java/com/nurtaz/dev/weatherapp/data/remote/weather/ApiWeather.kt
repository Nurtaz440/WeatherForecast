package com.nurtaz.dev.weatherapp.data.remote.weather

import com.nurtaz.dev.weatherapp.BuildConfig
import com.nurtaz.dev.weatherapp.data.remote.weather.dto.WeatherCurrentDto
import com.nurtaz.dev.weatherapp.data.remote.weather.dto.WeatherForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWeather {


    @GET("weather")
    suspend fun getCurrent(@Query("lat") lat: Float, @Query("lon") lon: Float,
                           @Query("appid") appId: String = BuildConfig.WEATHER_KEY
    ): Response<WeatherCurrentDto>

    @GET("forecast")
    suspend fun getForecast( @Query("lat") lat: Float,  @Query("lon") lon: Float,
//                             @Query("cnt") count: Int = 10,
                             @Query("appid") appId: String = BuildConfig.WEATHER_KEY
    ): Response<WeatherForecastDto>
}