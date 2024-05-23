package com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantDistributionDto(var native: List<String>) {
}