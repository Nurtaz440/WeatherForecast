package com.nurtaz.dev.weatherapp.data.base.remote

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Message(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ApiResult.Message(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiResult.Exception(e)
    }
}