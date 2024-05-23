package com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlantImageDto(var id: Int,
                         @Json(name = "image_url") var imageUrl: String ) {
}