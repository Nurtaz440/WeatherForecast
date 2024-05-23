package com.nurtaz.dev.weatherapp.domain.identify.usecases

import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.identify.repository.IdentifyRepository
import javax.inject.Inject

internal class IsAllowedUseCase  @Inject constructor(private var identifyRepository: IdentifyRepository)  {
    suspend operator fun invoke(): DomainResult<Boolean> = identifyRepository.isAllowed()
}