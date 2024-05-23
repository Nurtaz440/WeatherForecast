package com.nurtaz.dev.weatherapp.data.remote.identify.dto.inner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantIdentifyDto(val language: String,
                            val results:List<PlantIdentifiedResult>) {
}