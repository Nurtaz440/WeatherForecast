package com.nurtaz.dev.weatherapp.data.remote.ip

import com.nurtaz.dev.weatherapp.data.base.remote.handleApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteIpSource @Inject constructor(
    var apiIp: ApiIp
){

    suspend fun  getIp() = withContext(Dispatchers.IO) {

        return@withContext handleApi { apiIp.getIp() }


    }
}