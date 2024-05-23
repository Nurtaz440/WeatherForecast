package com.nurtaz.dev.weatherapp.data.remote.identify

import android.net.Uri
import com.nurtaz.dev.weatherapp.data.remote.identify.dto.inner.PlantIdentifyDto
import com.nurtaz.dev.weatherapp.domain.identify.model.ModelIdentified

fun PlantIdentifyDto.toDomainModel(uri: Uri): ModelIdentified {


    var keyworks = mutableListOf<String>()
    if(results.size>0){
        keyworks = results[0].species.commonNames.fold(keyworks) { acc: MutableList<String>, s: String ->
            acc.addAll(
                s.split(" ")
            )
            acc
        }

    }
    return ModelIdentified(
        keywords = keyworks,
        uriStr = uri.toString()
    )

}