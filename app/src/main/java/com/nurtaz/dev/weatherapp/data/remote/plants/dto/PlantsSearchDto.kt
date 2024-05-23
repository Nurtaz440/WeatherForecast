package com.nurtaz.dev.weatherapp.data.remote.plants.dto

import com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner.PlantsSearchEntryDto
import com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner.PlantsSearchMetaDto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantsSearchDto(
//    val id: String,
    val data: List<PlantsSearchEntryDto>,
    val meta: PlantsSearchMetaDto
) {
}