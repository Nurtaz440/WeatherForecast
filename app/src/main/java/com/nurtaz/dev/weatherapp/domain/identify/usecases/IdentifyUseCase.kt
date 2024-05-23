package com.nurtaz.dev.weatherapp.domain.identify.usecases

import android.net.Uri
import com.nurtaz.dev.weatherapp.domain.identify.repository.IdentifyRepository
import javax.inject.Inject

internal class IdentifyUseCase @Inject constructor(private var identifyRepository: IdentifyRepository)  {
    suspend operator fun invoke(organ: String, uri: Uri) = identifyRepository.identify(organ, uri)
}