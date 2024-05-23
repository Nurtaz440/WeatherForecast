package com.nurtaz.dev.weatherapp.data.local.db.weather

import androidx.room.Dao
import com.nurtaz.dev.weatherapp.data.local.db.base.RoomDaoBase

@Dao
interface WeatherForecastEntryDao : RoomDaoBase<ForecastEntryEntity> {


}