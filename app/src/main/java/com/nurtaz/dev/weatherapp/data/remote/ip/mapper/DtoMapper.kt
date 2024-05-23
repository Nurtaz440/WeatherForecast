package com.nurtaz.dev.weatherapp.data.remote.ip.mapper

import com.nurtaz.dev.weatherapp.data.remote.ip.dto.IpDto

fun IpDto.toLatLng(): Array<Float> {
    return arrayOf(lat, lon)
}