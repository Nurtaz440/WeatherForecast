package com.nurtaz.dev.weatherapp.data.base.remote

sealed interface ApiResult<T> {

    class Success<T>(val data: T) : ApiResult<T>

    class Message<T>(val code: Int, val message: String?) : ApiResult<T>

    class Exception<T>(val throwable: Throwable): ApiResult<T>


}