package com.nurtaz.dev.weatherapp.tools.livedata

class LiveDataEvent<out T>(private val data: T) {

    var isConsumed = false
        private set


    fun data(): T = data

    fun dataIfNotConsumed(): T? {
//        Log.d("LiveDataEvent", "isConsumed: $isConsumed")
        return if (isConsumed) {
            null
        } else {
            isConsumed = true
            data
        }
    }
}