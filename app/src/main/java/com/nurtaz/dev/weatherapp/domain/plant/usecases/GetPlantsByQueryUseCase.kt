package com.nurtaz.dev.weatherapp.domain.plant.usecases

import androidx.paging.PagingData
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantSearchEntryEntity
import com.nurtaz.dev.weatherapp.domain.plant.repository.PlantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetPlantsByQueryUseCase  @Inject constructor(private var plantRepository: PlantRepository)  {

    operator fun invoke(q:String ): Flow<PagingData<PlantSearchEntryEntity>> = plantRepository.searchPaginated(q)
}