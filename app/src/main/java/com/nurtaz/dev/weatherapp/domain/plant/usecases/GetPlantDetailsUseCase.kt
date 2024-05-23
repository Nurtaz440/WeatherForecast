package com.nurtaz.dev.weatherapp.domain.plant.usecases

import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantEntity
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.plant.repository.PlantRepository
import javax.inject.Inject

class GetPlantDetailsUseCase @Inject constructor(private var plantRepository: PlantRepository)  {

    suspend operator fun invoke(): DomainResult<List<PlantEntity>> = plantRepository.getRecentPlantList()
}