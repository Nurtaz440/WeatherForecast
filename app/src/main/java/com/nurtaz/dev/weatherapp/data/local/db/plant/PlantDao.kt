package com.nurtaz.dev.weatherapp.data.local.db.plant

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.nurtaz.dev.weatherapp.data.local.db.base.RoomDaoBase


@Dao
interface PlantDao: RoomDaoBase<PlantSearchEntryEntity> {
    @Query("SELECT * FROM Plants WHERE id = :plantId")
    suspend fun byPlantId(plantId: Int): PlantEntity



    @Query("DELETE FROM Plants")
    fun deleteAll()

}