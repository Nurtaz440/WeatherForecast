package com.nurtaz.dev.weatherapp.domain.plant.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.nurtaz.dev.weatherapp.data.base.locale.LocalResult
import com.nurtaz.dev.weatherapp.data.base.remote.ApiResult
import com.nurtaz.dev.weatherapp.data.local.assets.entities.ModelCategory
import com.nurtaz.dev.weatherapp.data.local.db.AppDatabase
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantDetailRoomData
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantRecentSearchEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantSearchEntryEntity
import com.nurtaz.dev.weatherapp.data.local.source.CachedRemoteKeySource
import com.nurtaz.dev.weatherapp.data.remote.plants.RemotePlantsConstants
import com.nurtaz.dev.weatherapp.data.remote.plants.RemotePlantsSource
import com.nurtaz.dev.weatherapp.data.remote.plants.getImageEntities
import com.nurtaz.dev.weatherapp.data.remote.plants.toRoomEntity
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.plant.repository.PlantRepository
import com.nurtaz.dev.weatherapp.domain.plant.search.PlantsSearchFilterEdiblePartMediator
import com.nurtaz.dev.weatherapp.domain.plant.search.PlantsSearchMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.nurtaz.dev.weatherapp.data.local.source.LocalPlantSource

@Singleton
class DefaultPlantRepository @Inject constructor(
    private val remotePlantsSource: RemotePlantsSource,
    private val localPlantSource: LocalPlantSource,

    private var cachedRemoteKeySource: CachedRemoteKeySource,
    val db: AppDatabase
) : PlantRepository {


    @OptIn(ExperimentalPagingApi::class)
    override fun searchPaginated(q: String): Flow<PagingData<PlantSearchEntryEntity>> {
        var qFormatted = q.lowercase()
        val pagingSourceFactory =
            { localPlantSource.getPlantsSearchPageSource(qFormatted) }
        return Pager(
            config = PagingConfig(
                RemotePlantsConstants.PAGE_SIZE,
                maxSize = 300
            ),//  enablePlaceholders = true

            remoteMediator = PlantsSearchMediator(
                qFormatted,
                remotePlantsSource,
                localPlantSource,
                cachedRemoteKeySource,
                db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun filterPaginated(
        filterForEdible: Boolean,
        q: String
    ): Flow<PagingData<PlantSearchEntryEntity>> {
        val qFormatted = q.lowercase()

        val pagingSourceFactory =
            { localPlantSource.getPlantsSearchPageSource("$filterForEdible:$qFormatted") }
        return Pager(
            config = PagingConfig(
                RemotePlantsConstants.PAGE_SIZE,
                maxSize = 300
            ),//  enablePlaceholders = true

            remoteMediator = PlantsSearchFilterEdiblePartMediator(
                filterForEdible, qFormatted, remotePlantsSource,
                localPlantSource, cachedRemoteKeySource, db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun recentSearchPaginated(q: String?): Flow<PagingData<PlantRecentSearchEntity>> {
        val qFormatted = q?.lowercase()
        val pagingSourceFactory =
            { localPlantSource.getPlantRecentSearchPageSource(qFormatted) }


        return Pager(
            config = PagingConfig(
                RemotePlantsConstants.PAGE_SIZE,
                maxSize = 300
            ),//  enablePlaceholders = true
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override suspend fun recentSearchList(): DomainResult<List<PlantRecentSearchEntity>> {
        var localresult = localPlantSource.getPlantRecentSearchList()
        return DomainResult.Success(localresult.data)
    }

    override suspend fun addRecentSearches(search: List<PlantRecentSearchEntity>): DomainResult<List<Long>> {
        var localresult = localPlantSource.addRecentSearch(search)
        /*when (localresult) {
            is LocalResult.Success -> {
                return DomainResult.Success(localresult.data)
            }
            is LocalResult.Exception -> {
                return DomainResult.Failure(localresult.throwable)
            }
            is LocalResult.Message -> {
                return  DomainResult.Failure(Throwable(localresult.message))
            }
        }*/
        return DomainResult.Success(localresult.data)
    }

    override suspend fun getPlantDetail(plantId: Int): DomainResult<PlantDetailRoomData> {
        var localresult = localPlantSource.getPlantDetail(plantId)
        if (localresult is LocalResult.Success) {
            return DomainResult.Success(localresult.data)
        }

        var remoteResult = remotePlantsSource.plantDetailById(plantId)
        val remotePlant = if (remoteResult is ApiResult.Success && remoteResult.data != null) {
            remoteResult.data.data
        } else {
            null
        }
        if (remotePlant == null) {

            if (remoteResult is ApiResult.Exception) {
                remoteResult.throwable.printStackTrace()
            }
            return DomainResult.Failure(Throwable("Unable to get Plant"))

        }

        db.withTransaction {
            var id = localPlantSource.addPlant(remotePlant.toRoomEntity())

            var ids = localPlantSource.addPlantImages(remotePlant.getImageEntities())


        }

        localresult = localPlantSource.getPlantDetail(plantId)

        if (localresult is LocalResult.Success) {
            return DomainResult.Success(localresult.data)
        }
        return DomainResult.Failure(Throwable("Finally failed"))

    }

    override suspend fun getRecentPlantList(): DomainResult<List<PlantEntity>> {
        var localresult = localPlantSource.getRecentPlantDetails()
        return DomainResult.Success(localresult.data)
        /*if(localresult is LocalResult.Exception){
            localresult.throwable.printStackTrace()
        }*/

//        return  DomainResult.Failure(Throwable("Finally failed"))


    }

    override suspend fun getCategoryList(): DomainResult<List<ModelCategory>> {
        var localresult = localPlantSource.getCategories()
        if (localresult is LocalResult.Success) {
            return DomainResult.Success(localresult.data.items)
        }
        if (localresult is LocalResult.Exception) {
            localresult.throwable.printStackTrace()
        }

        return DomainResult.Failure(Throwable("Finally failed"))


    }

    override suspend fun clearData(): DomainResult.Success<Unit> {
        localPlantSource.removeAll()
        withContext(Dispatchers.IO) { cachedRemoteKeySource.deleteAll() }
        return DomainResult.Success(Unit)
    }
}