package com.nurtaz.dev.weatherapp.data.local.db.location

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Location", indices = [Index(value = ["lat", "lon"], unique = true)])
data class LocationRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val lat: Float,
    val lon: Float,
    var weatherUnix:Int? = null,
    var forecastUnix: Int? = null) {
}