package com.nurtaz.dev.weatherapp.data.remote.identify

import com.nurtaz.dev.weatherapp.BuildConfig
import com.nurtaz.dev.weatherapp.data.remote.identify.dto.inner.PlantIdentifyDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiIdentify {
    @Multipart
    @POST("identify/{project}")
    suspend fun identify(@Path("project") project: String = "all",
                         @Query("api-key") token: String = BuildConfig.PLANTNET_KEY,
                         @PartMap() partMap: MutableMap<String, RequestBody>,
                         @Part images: MultipartBody.Part
    ): Response<PlantIdentifyDto>
}