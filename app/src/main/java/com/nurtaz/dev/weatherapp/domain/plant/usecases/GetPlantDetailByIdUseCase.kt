package com.nurtaz.dev.weatherapp.domain.plant.usecases

import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantDetailRoomData
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.plant.repository.PlantRepository
import javax.inject.Inject

class GetPlantDetailByIdUseCase @Inject constructor(private var plantRepository: PlantRepository)  {

    suspend operator fun invoke(plantId: Int): DomainResult<PlantDetailRoomData> = plantRepository.getPlantDetail(plantId)
}