package com.nurtaz.dev.weatherapp.data.remote.plants.dto

import com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner.PlantFamilyDto
import com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner.PlantGenusDto
import com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner.PlantSpeciesDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantDetailDto(var id: Int,
                          @Json(name = "main_species_id") var mainSpeciesId: Int,
                          @Json(name = "image_url" ) val imageUrl: String,
                          val vegetable: Boolean?,
                          @Json(name = "common_name" ) val commonName: String?,
                          @Json(name = "scientific_name" ) val scientificName: String?,
                          val family: PlantFamilyDto,
                          val genus: PlantGenusDto,
                          @Json(name = "main_species") val mainSpecies: PlantSpeciesDto
) {

}