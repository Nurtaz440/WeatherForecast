package com.nurtaz.dev.weatherapp.data.local.db.plant

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.nurtaz.dev.weatherapp.data.local.db.base.RoomDaoBase

@Dao
interface PlantDetailDao : RoomDaoBase<PlantEntity> {

    @Transaction
    @Query("SELECT * FROM Plants WHERE id=:plantId")
    suspend fun byId(plantId:Int): PlantDetailRoomData?
    @Query("SELECT * FROM Plants ORDER BY `lastQueriedDt` DESC LIMIT :limit")
    fun list(limit: Int): List<PlantEntity>

}