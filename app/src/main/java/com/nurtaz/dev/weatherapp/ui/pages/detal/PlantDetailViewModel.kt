package com.nurtaz.dev.weatherapp.ui.pages.detal

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantDetailRoomData
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.plant.usecases.GetPlantDetailByIdUseCase
import com.nurtaz.dev.weatherapp.ui.base.ViewModelBase
import com.nurtaz.dev.weatherapp.ui.nav.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PlantDetailViewModel @Inject constructor(

    private val navManager: NavManager,
    var getPlantDetailByIdUseCase: GetPlantDetailByIdUseCase
): ViewModelBase() {
    private lateinit var mPlantDetail: PlantDetailRoomData
    private var mPlantId: Int = -1

    var bKeywords = MutableLiveData<List<String>>()

    val bIsProgress = MutableLiveData(false)
    val bData = MutableLiveData<PlantDetailRoomData>()


    fun initArgs(args: PlantDetailFragmentArgs) {
        mPlantId = args.plantId
        viewModelScope.launch {
            bIsProgress.postValue(true)
            getDetail()
            bIsProgress.postValue(false)
        }
    }

    var bActionGallery = View.OnClickListener { view ->
        navigateToResult(mPlantDetail.entries.map{it.imageUrl})
//            _mEventOnActionGallery.postValue(LiveDataEvent(mPlantDetail.entries.map{it.imageUrl}))
    }
    //getPlantDetailByIdUseCase

    private suspend fun getDetail() {
//        identifyUseCase.invoke("organ");
        if(mPlantId == -1){
            return
        }
        var result = getPlantDetailByIdUseCase.invoke(mPlantId)
        if (result is DomainResult.Success) {
            result.value.let {
                bData.postValue(it)
                bKeywords.postValue(it.detail.natives.take(7))
                mPlantDetail = it
            }
            Log.d("PlantDetailViewModel", "details: ${result.value}")
        }else {
            if(result is DomainResult.Failure){
                result.throwable?.printStackTrace()
            }
        }

    }
    private fun navigateToResult(images: List<String>) {
        if(images.isEmpty())return
        navManager.navigate(PlantDetailFragmentDirections.navigateToSlider(images.toTypedArray()))
    }

}