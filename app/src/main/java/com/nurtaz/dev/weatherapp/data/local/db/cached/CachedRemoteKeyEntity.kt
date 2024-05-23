package com.nurtaz.dev.weatherapp.data.local.db.cached

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "CachedRemoteKey", indices = [Index(value = ["q", "refId", "refType"], unique = true)])
data class CachedRemoteKeyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val refId: Int,
    val q:String = "",
    val refType:Int=0,
    val nextKey: Int?,
    val prevKey: Int?,

    val isEndReached: Boolean
) {
}