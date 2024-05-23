package com.nurtaz.dev.weatherapp.data.local.db.plant

import androidx.room.Embedded
import androidx.room.Relation

data class PlantDetailRoomData(
    @Embedded
    val detail: PlantEntity,
    @Relation(parentColumn = "id", entityColumn = "plantId")
    val entries: List<PlantImageEntity> = emptyList()
) {
}