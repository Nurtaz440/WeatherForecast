package com.nurtaz.dev.weatherapp.domain.chart

import android.hardware.SensorManager
import android.util.SparseIntArray

class ChartConstants {

    val MAP_XAXIS_TYPE_TO_TIMESTAMP: SparseIntArray = object : SparseIntArray() {
        init {
            put(SensorManager.SENSOR_DELAY_NORMAL, 200)
            put(SensorManager.SENSOR_DELAY_UI, 60)
            put(SensorManager.SENSOR_DELAY_GAME, 20)
            put(SensorManager.SENSOR_DELAY_FASTEST, 10)
        }
    }
}