package com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantSpeciesDto(var id: String,
                           @Json(name = "common_name" )  var name: String,
                           val edible: Boolean?,
                           @Json(name = "edible_part") val ediblePart: List<String> ?= listOf(),

                           val images : Map<String, List<PlantImageDto>>,
                           val growth: PlantGrowthDto,

                           val distribution: PlantDistributionDto

) {
}