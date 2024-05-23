package com.nurtaz.dev.weatherapp.domain.plant.usecases

import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantRecentSearchEntity
import com.nurtaz.dev.weatherapp.domain.plant.repository.PlantRepository
import javax.inject.Inject

class AddPlantRecentSearchesUseCase @Inject constructor(private var plantRepository: PlantRepository)  {

    suspend operator fun invoke(recentSearch:List<PlantRecentSearchEntity> ) = plantRepository.addRecentSearches(recentSearch)
}