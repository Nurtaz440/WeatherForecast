package com.nurtaz.dev.weatherapp.domain.identify.imple

import android.content.Context
import android.net.Uri
import android.util.Log
import com.nurtaz.dev.weatherapp.data.base.remote.ApiResult
import com.nurtaz.dev.weatherapp.data.local.db.log.IdentifyLogRoomEntity
import com.nurtaz.dev.weatherapp.data.local.source.LocalLogSource
import com.nurtaz.dev.weatherapp.data.remote.identify.RemoteIdentifySource
import com.nurtaz.dev.weatherapp.data.remote.identify.toDomainModel
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.identify.IdentifyConstants
import com.nurtaz.dev.weatherapp.domain.identify.model.ModelIdentified
import com.nurtaz.dev.weatherapp.domain.identify.repository.IdentifyRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import me.shouheng.compress.Compress
import me.shouheng.compress.concrete
import java.io.File
import java.util.Calendar
import javax.inject.Inject

class DefaultIdentifyRepository  @Inject constructor(
    @ApplicationContext private val context: Context,
    private val remoteIdentifySource: RemoteIdentifySource,
    private val localLogSource: LocalLogSource
): IdentifyRepository {

    //    fun identify()
    override suspend fun identify(organ: String, uri: Uri): DomainResult<ModelIdentified> {

        val file  = compressFile(uri)

        /*        val compressedImageFile = Compressor.compress(context, uri) {
                    default(width = 640, format = Bitmap.CompressFormat.JPEG)
                }*/
        val apiResult = remoteIdentifySource.identify(organ, file)
        if(apiResult is ApiResult.Success){
            var calender = Calendar.getInstance()
//            calender.add(Calendar.DAY_OF_YEAR, -1);

            localLogSource.insert(IdentifyLogRoomEntity(dt=(calender.time.time/1000L).toInt()))
            // TODO add into log
//            Log.d("DefaultIdentityRepository", "identify: ${localresult.data}")
            return DomainResult.Success(apiResult.data.toDomainModel(uri))
        }else if(apiResult is ApiResult.Exception){
            apiResult.throwable.printStackTrace()
        }else if(apiResult is ApiResult.Message){

            return  DomainResult.Failure(Throwable("${apiResult.message}"))
        }

        return DomainResult.Failure(Throwable("some"))
    }

    override suspend fun isAllowed(): DomainResult<Boolean> {
        var countResult = localLogSource.getCountForDay()

        Log.d("DefaultIdentityRepository", "isAllowed: $countResult")
        if(countResult<= IdentifyConstants.ALLOWED_COUNT){
            return DomainResult.Success(true)
        }
        return DomainResult.Success(false)


    }

    suspend fun compressFile(uri: Uri): File {
        val file  = Compress.with(context, uri).concrete {
            withMaxHeight(500f)
            withMaxWidth(500f)
        }.get(Dispatchers.IO)

        return file
    }

}