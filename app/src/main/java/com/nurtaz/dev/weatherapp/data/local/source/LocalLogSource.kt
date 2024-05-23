package com.nurtaz.dev.weatherapp.data.local.source

import com.nurtaz.dev.weatherapp.data.local.db.log.IdentifyLogRoomDao
import com.nurtaz.dev.weatherapp.data.local.db.log.IdentifyLogRoomEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalLogSource @Inject constructor(
    private var identifyLogRoomDao: IdentifyLogRoomDao
) {
    suspend fun insert(entity: IdentifyLogRoomEntity) = withContext(Dispatchers.IO) {
        return@withContext identifyLogRoomDao.insert(entity)
    }

    suspend fun getCountForDay() = withContext(Dispatchers.IO){

        var calender = Calendar.getInstance()
        calender.add(Calendar.DAY_OF_YEAR, -1);

        return@withContext identifyLogRoomDao.count((calender.timeInMillis/1000).toInt())
    }
}