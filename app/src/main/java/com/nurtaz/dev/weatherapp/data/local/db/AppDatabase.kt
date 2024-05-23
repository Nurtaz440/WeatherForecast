package com.nurtaz.dev.weatherapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nurtaz.dev.weatherapp.data.local.db.base.converter.StringListConverter
import com.nurtaz.dev.weatherapp.data.local.db.cached.CachedRemoteKeyDao
import com.nurtaz.dev.weatherapp.data.local.db.cached.CachedRemoteKeyEntity
import com.nurtaz.dev.weatherapp.data.local.db.location.LocationRoomDao
import com.nurtaz.dev.weatherapp.data.local.db.location.LocationRoomEntity
import com.nurtaz.dev.weatherapp.data.local.db.log.IdentifyLogRoomDao
import com.nurtaz.dev.weatherapp.data.local.db.log.IdentifyLogRoomEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantDao
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantDetailDao
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantImageDao
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantImageEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantRecentSearchDao
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantRecentSearchEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantSearchEntryEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantsSearchDao
import com.nurtaz.dev.weatherapp.data.local.db.weather.ForecastEntryEntity
import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherCurrentDao
import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherCurrentRoomEntity
import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherForecastDao
import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherForecastEntryDao
import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherForecastRoomEntity

@Database(
    entities = [
        LocationRoomEntity::class,
        WeatherCurrentRoomEntity::class,
        WeatherForecastRoomEntity::class,
        ForecastEntryEntity::class,
        PlantSearchEntryEntity::class,
        CachedRemoteKeyEntity::class,
        PlantRecentSearchEntity::class,
        IdentifyLogRoomEntity::class,
        PlantEntity::class,
        PlantImageEntity::class,],
    version = 11,
    exportSchema = true
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun locationDao(): LocationRoomDao
    abstract fun weatherCurrent(): WeatherCurrentDao
    abstract fun weatherForecast(): WeatherForecastDao
    abstract fun weatherForecastEntry(): WeatherForecastEntryDao
    abstract fun cachedRemoteKey(): CachedRemoteKeyDao
    abstract fun plantsSearchDao(): PlantsSearchDao
    abstract fun plantRecentSearchDao(): PlantRecentSearchDao
    abstract fun identifyLogDao(): IdentifyLogRoomDao

    abstract fun plantDao(): PlantDao
    abstract fun plantDetailDao(): PlantDetailDao
    abstract fun plantImageDao(): PlantImageDao

}