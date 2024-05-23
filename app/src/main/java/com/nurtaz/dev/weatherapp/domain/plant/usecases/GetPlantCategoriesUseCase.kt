package com.nurtaz.dev.weatherapp.domain.plant.usecases

import com.nurtaz.dev.weatherapp.data.local.assets.entities.ModelCategory
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.plant.repository.PlantRepository
import javax.inject.Inject

class GetPlantCategoriesUseCase @Inject constructor(private var plantRepository: PlantRepository)  {

    suspend operator fun invoke(): DomainResult<List<ModelCategory>> = plantRepository.getCategoryList()
}