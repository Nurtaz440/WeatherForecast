package com.nurtaz.dev.weatherapp.data.local.db.weather

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nurtaz.dev.weatherapp.data.local.db.location.LocationRoomEntity
import com.nurtaz.dev.weatherapp.data.local.db.weather.inner.WeatherAttrEntity
import com.nurtaz.dev.weatherapp.data.local.db.weather.inner.WeatherSubEntity

@Entity(tableName = "WeatherCurrent",
    indices=[Index("locationId"), Index("dt")],
    foreignKeys =
    [ForeignKey(
        onDelete = CASCADE, entity = LocationRoomEntity::class, parentColumns = ["id"],
        childColumns = ["locationId"]
    )]
)
data class WeatherCurrentRoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val locationId: Int,
    val visibility: Int,
    val cityId: Int,
    val dt: Int,
    @Embedded(prefix = "weather_")  val weather: WeatherSubEntity?,
    @Embedded(prefix = "attr_") val attrs: WeatherAttrEntity
) {
}