package com.nurtaz.dev.weatherapp.data.local.db.weather

import androidx.room.Embedded
import androidx.room.Relation

data class WeatherForecastRoomData(
    @Embedded
    val forecast: WeatherForecastRoomEntity,
    @Relation(parentColumn = "id", entityColumn = "forecastId")
    val entries: List<ForecastEntryEntity> = emptyList()
) {
}