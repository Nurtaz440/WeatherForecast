package com.nurtaz.dev.weatherapp.tools.livedata

import android.util.Log
import androidx.lifecycle.Observer

class LiveDataObserver<T>(private val onEvent: (T) -> Unit) : Observer<LiveDataEvent<T>> {
    override fun onChanged(event: LiveDataEvent<T>) {


        Log.d("LiveDataObserver", "onChanged: ")
        event.dataIfNotConsumed()?.let {
            onEvent(it)
        }
    }
}