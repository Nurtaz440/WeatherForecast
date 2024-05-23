package com.nurtaz.dev.weatherapp.data.local.db.base.converter

import android.util.Log
import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        if(stringListString.isEmpty()){
            return emptyList()
        }
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {

        Log.d("StringListConverter", "toString: ${stringList.size}")
        if(stringList.isEmpty()){
            return  ""
        }
        return stringList.joinToString(separator = ",")
    }
}