package com.nurtaz.dev.weatherapp.data.remote.plants

import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantImageEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantSearchEntryEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.inner.PlantGrowthEntity
import com.nurtaz.dev.weatherapp.data.remote.plants.dto.PlantDetailDto
import com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner.PlantGrowthDto
import com.nurtaz.dev.weatherapp.data.remote.plants.dto.inner.PlantsSearchEntryDto
import java.util.Calendar

fun PlantsSearchEntryDto.toRoomEntity(position: Int, q: String): PlantSearchEntryEntity {

//    val first = weather.firstOrNull()
    return PlantSearchEntryEntity(
        q = q,
        index = position,
        commonName = commonName ?: "",
        family = family,
        imageUrl = imageUrl,
        plantId = id,
        slug = slug,
        scientificName = scientificName ?: ""
    )

}

fun PlantDetailDto.toRoomEntity(): PlantEntity {


    return PlantEntity(
        id = mainSpeciesId, imageUrl = imageUrl, commonName = commonName,
        scientificName = scientificName,
        vegetable = vegetable,
        speciesId = mainSpeciesId,
        edible = mainSpecies.edible,
        ediblePart = mainSpecies.ediblePart?: listOf(),
        familyName = family.name,
        genusName = genus.name,
        speciesName = mainSpecies.name,
        growth = mainSpecies.growth.toRoomEntity(),
        lastQueriedDt = (Calendar.getInstance().time.time/1000).toInt(),
        natives = mainSpecies.distribution.native

    )
}

fun PlantDetailDto.getImageEntities(): List<PlantImageEntity> {
    var has = mainSpecies.images.containsKey("")

    var images: List<PlantImageEntity>? = null
    if (has) {
        images = mainSpecies.images[""]?.map { PlantImageEntity(imageId = it.id, key="",
            plantId = mainSpeciesId, imageUrl = it.imageUrl) } ?: listOf()

    } else {
        images = mainSpecies.images.entries.fold(mutableListOf<PlantImageEntity>()) { acc, entry ->
            acc.addAll(entry.value.map { PlantImageEntity(imageId = it.id, key=entry.key,
                plantId = mainSpeciesId, imageUrl = it.imageUrl) })
            acc
        }
    }

    return images?: listOf()


}


fun PlantGrowthDto.toRoomEntity(): PlantGrowthEntity {


    return PlantGrowthEntity(
        phMaximum = phMaximum, phMinimum = phMinimum,
        atmHumidity = atmosphericHumidity, bloomMonths = bloomMonths?: listOf(),
        fruitMonths = fruitMonths?: listOf(), growthMonths = growthMonths?: listOf(),
        light = light,
        soilHumidity = soilHumidity, soilNutriments = soilNutriments, soilSalinity = soilSalinity
    )
}