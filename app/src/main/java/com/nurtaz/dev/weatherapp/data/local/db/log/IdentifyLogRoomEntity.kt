package com.nurtaz.dev.weatherapp.data.local.db.log

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "IdentifyLog", indices = [Index(value = ["dt"], unique = true)])
data class IdentifyLogRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val dt: Int
) {
}