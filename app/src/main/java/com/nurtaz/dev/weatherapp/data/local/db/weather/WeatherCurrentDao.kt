package com.nurtaz.dev.weatherapp.data.local.db.weather

import androidx.room.Dao
import androidx.room.Query
import com.nurtaz.dev.weatherapp.data.local.db.base.RoomDaoBase

@Dao
interface WeatherCurrentDao: RoomDaoBase<WeatherCurrentRoomEntity> {

    @Query("DELETE FROM WeatherCurrent WHERE locationId=:locationId")
    suspend fun deleteByLocationId(locationId:Int): Int


    @Query("SELECT * FROM WeatherCurrent WHERE locationId=:locationId ORDER BY dt ASC LIMIT :limit")
    suspend fun findByLocationId(locationId:Int, limit: Int = 1): List<WeatherCurrentRoomEntity>

    @Query("DELETE FROM WeatherCurrent")
    fun deleteAll()
}