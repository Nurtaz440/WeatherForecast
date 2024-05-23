package com.nurtaz.dev.weatherapp.data.local.db.cached

import androidx.room.Dao
import androidx.room.Query
import com.nurtaz.dev.weatherapp.data.local.db.base.RoomDaoBase

@Dao
interface CachedRemoteKeyDao: RoomDaoBase<CachedRemoteKeyEntity> {

    @Query("SELECT * FROM CachedRemoteKey WHERE refId=:refId AND refType=:refType AND q=:q LIMIT :limit")
    suspend fun getKey(refId:Int,refType:Int, q:String, limit: Int = 1): List<CachedRemoteKeyEntity>

    @Query("SELECT * FROM CachedRemoteKey WHERE  refType=:refType AND q=:q ORDER BY id ASC LIMIT :limit")
    suspend fun getKeyFirst(refType:Int, q:String, limit: Int = 1): List<CachedRemoteKeyEntity>
    @Query("DELETE FROM CachedRemoteKey")
    fun deleteAll()

    @Query("Select count(*) from CachedRemoteKey")
    fun getCount(): Int

}