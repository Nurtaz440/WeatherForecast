package com.nurtaz.dev.weatherapp.domain.plant.repository

import androidx.paging.PagingData
import com.nurtaz.dev.weatherapp.data.local.assets.entities.ModelCategory
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantDetailRoomData
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantRecentSearchEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantSearchEntryEntity
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    fun searchPaginated(q: String): Flow<PagingData<PlantSearchEntryEntity>>
    fun filterPaginated(filterForEdible: Boolean, q: String): Flow<PagingData<PlantSearchEntryEntity>>
    //PagingData<PlantSearchEntryEntity>
    fun recentSearchPaginated(q: String?): Flow<PagingData<PlantRecentSearchEntity>>

    suspend fun recentSearchList(): DomainResult<List<PlantRecentSearchEntity>>

    suspend fun addRecentSearches(search: List<PlantRecentSearchEntity>): DomainResult<List<Long>>
    suspend fun getPlantDetail(plantId: Int): DomainResult<PlantDetailRoomData>

    suspend fun getRecentPlantList(): DomainResult<List<PlantEntity>>
    suspend fun getCategoryList(): DomainResult<List<ModelCategory>>
    suspend fun clearData(): DomainResult<Unit>

}