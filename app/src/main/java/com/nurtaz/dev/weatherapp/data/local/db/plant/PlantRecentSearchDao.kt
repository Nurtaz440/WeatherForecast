package com.nurtaz.dev.weatherapp.data.local.db.plant

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.nurtaz.dev.weatherapp.data.local.db.base.RoomDaoBase

@Dao
interface PlantRecentSearchDao : RoomDaoBase<PlantRecentSearchEntity> {

    @Query("SELECT * FROM PlantRecentSearch WHERE refType=:refType AND q IS NOT NULL AND q != \"\" ORDER BY `dt` DESC")
    fun paginated(refType: Int): PagingSource<Int, PlantRecentSearchEntity>

    @Query("SELECT * FROM PlantRecentSearch WHERE refType=:refType AND q LIKE :q ORDER BY `dt` DESC")
    fun paginated(q: String, refType: Int): PagingSource<Int, PlantRecentSearchEntity>
    @Query("SELECT * FROM PlantRecentSearch WHERE refType=:refType AND q IS NOT NULL AND q != \"\" ORDER BY `dt` DESC LIMIT :limit")
    fun list(limit: Int, refType: Int): List<PlantRecentSearchEntity>


}