package com.nurtaz.dev.weatherapp.domain.identify.repository

import android.net.Uri
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.identify.model.ModelIdentified

interface IdentifyRepository {

    suspend fun identify(organ: String, uri: Uri): DomainResult<ModelIdentified>
    suspend fun isAllowed(): DomainResult<Boolean>
}