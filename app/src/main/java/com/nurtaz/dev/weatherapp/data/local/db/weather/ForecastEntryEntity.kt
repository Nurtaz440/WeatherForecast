package com.nurtaz.dev.weatherapp.data.local.db.weather

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nurtaz.dev.weatherapp.data.local.db.weather.inner.WeatherAttrEntity
import com.nurtaz.dev.weatherapp.data.local.db.weather.inner.WeatherSubEntity

@Entity(
    tableName = "ForecastEntry",

    indices = [Index("forecastId"), Index("dt")],
    foreignKeys = [ForeignKey(
        onDelete = ForeignKey.CASCADE,
        entity = WeatherForecastRoomEntity::class,
        parentColumns = ["id"],
        childColumns = ["forecastId"]
    )]
)
data class ForecastEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val forecastId: Int,
    val dt: Int,
    @Embedded(prefix = "weather_") val weather: WeatherSubEntity?,
    @Embedded(prefix = "attr_") val attrs: WeatherAttrEntity
) {
}