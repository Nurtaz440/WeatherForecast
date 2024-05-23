package com.nurtaz.dev.weatherapp.data.local.db.weather

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nurtaz.dev.weatherapp.data.local.db.location.LocationRoomEntity

@Entity(tableName = "WeatherForecast",

    indices=[Index("locationId"), Index("dt")],
    foreignKeys =
    [ForeignKey(
        onDelete = ForeignKey.CASCADE, entity = LocationRoomEntity::class, parentColumns = ["id"],
        childColumns = ["locationId"],
    )])
data class WeatherForecastRoomEntity(
    @PrimaryKey(autoGenerate = true)  val id:Int = 0,
    val count: Int,
    val dt: Int,
    val locationId: Int,
) {
}