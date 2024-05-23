package com.nurtaz.dev.weatherapp.data.remote.ip.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IpDto(var query: String, var lat: Float, var lon: Float){

}