package com.nurtaz.dev.weatherapp.domain.plant.usecases

import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.plant.repository.PlantRepository
import javax.inject.Inject

class DeleteAllPlantsUseCase  @Inject constructor(private var plantRepository: PlantRepository)  {

    suspend operator fun invoke( ): DomainResult<Unit> = plantRepository.clearData()
}