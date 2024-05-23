package com.nurtaz.dev.weatherapp.data.local.db.plant

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "PlantImages",

    indices=[Index("plantId"), Index("key")],
    foreignKeys =
    [ForeignKey(
        onDelete = ForeignKey.CASCADE, entity = PlantEntity::class, parentColumns = ["id"],
        childColumns = ["plantId"],
    )])
data class PlantImageEntity(
    @PrimaryKey(autoGenerate = true)  val id:Int = 0,
    var imageId: Int,
    var plantId: Int,
    var key: String,
    var imageUrl: String
) {
}