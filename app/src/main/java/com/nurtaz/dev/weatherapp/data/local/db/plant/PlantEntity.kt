package com.nurtaz.dev.weatherapp.data.local.db.plant

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nurtaz.dev.weatherapp.data.local.db.plant.inner.PlantGrowthEntity

@Entity(tableName = "Plants",  indices = [ Index("lastQueriedDt")])
data class PlantEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int ,
    var speciesId: Int?,
    val imageUrl: String, // TODO make this nullable
    val vegetable: Boolean?,
    val commonName: String?,
    val familyName: String?,
    val genusName: String?,
    val scientificName: String?,
    val speciesName: String?,
    val edible: Boolean?,
    val ediblePart: List<String>,
    val natives: List<String>,
    var lastQueriedDt: Int,
    @Embedded(prefix = "growth_")  val growth: PlantGrowthEntity?

) {

    companion object {
        var diffCallback: DiffUtil.ItemCallback<PlantEntity> =
            object : DiffUtil.ItemCallback<PlantEntity>() {

                override fun areItemsTheSame(
                    oldItem: PlantEntity,
                    newItem: PlantEntity
                ): Boolean {
                    return oldItem.id == newItem.id                }

                override fun areContentsTheSame(
                    oldItem: PlantEntity,
                    newItem: PlantEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}