package com.nurtaz.dev.weatherapp.data.local.db.plant

import androidx.room.Dao
import androidx.room.Query
import com.nurtaz.dev.weatherapp.data.local.db.base.RoomDaoBase

@Dao
interface PlantImageDao: RoomDaoBase<PlantImageEntity> {

    @Query("DELETE FROM PlantImages")
    fun deleteAll()

}