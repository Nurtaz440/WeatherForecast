package com.nurtaz.dev.weatherapp.data.base.locale

sealed interface LocalResult<T> {

    class Success<T>(val data: T) : LocalResult<T>

    class Message<T>(val code: Int, val message: String?) : LocalResult<T>

    class Exception<T>(val throwable: Throwable): LocalResult<T>


}