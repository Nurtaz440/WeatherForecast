package com.nurtaz.dev.weatherapp.ui.pages.nav.plants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantRecentSearchEntity
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.plant.usecases.GetPlantDetailsUseCase
import com.nurtaz.dev.weatherapp.domain.plant.usecases.GetPlantRecentSearchListUseCase
import com.nurtaz.dev.weatherapp.tools.livedata.LiveDataEvent
import com.nurtaz.dev.weatherapp.ui.base.ViewModelBase
import com.nurtaz.dev.weatherapp.ui.nav.NavManager
import com.nurtaz.dev.weatherapp.ui.pages.nav.home.NavHomeFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NavPlantsViewModel @Inject
constructor(private val navManager: NavManager,
            var recentSearchListUseCase: GetPlantRecentSearchListUseCase,
            var plantDetailsUseCase: GetPlantDetailsUseCase
): ViewModelBase(){

    private val _mEventRecentSearchList = MutableLiveData<LiveDataEvent<List<PlantRecentSearchEntity>>>()
    val mEventRecentSearchList: LiveData<LiveDataEvent<List<PlantRecentSearchEntity>>> = _mEventRecentSearchList

    private val _mEventRecentPlantList = MutableLiveData<LiveDataEvent<List<PlantEntity>>>()
    val mEventRecentPlantList: LiveData<LiveDataEvent<List<PlantEntity>>> = _mEventRecentPlantList

    val bIsProgress = MutableLiveData(false)
    val bEmptyPlants = MutableLiveData(false)
    val bEmptySearch = MutableLiveData(false)
    /*init {
        initList()
    }*/

    fun initList(){
        viewModelScope.launch {
            getRecent()
        }
    }

    private suspend fun getRecent() {

        bIsProgress.postValue(true)
        var result = recentSearchListUseCase.invoke()

        var plantsResult = plantDetailsUseCase.invoke()

        bIsProgress.postValue(false)
        if (result is DomainResult.Success) {
            result.value.let {
                _mEventRecentSearchList.postValue(LiveDataEvent(it))
                if(it.isNotEmpty()){
                    bEmptySearch.postValue(false)
                }else{
                    bEmptySearch.postValue(true)
                }
            }


//            Log.d("PlantDetailViewModel", "details: ${result.value}")
        }else {
            bEmptySearch.postValue(false)
            if(result is DomainResult.Failure){
                result.throwable?.printStackTrace()
            }
        }
        if(plantsResult is DomainResult.Success){
            _mEventRecentPlantList.postValue(LiveDataEvent(plantsResult.value))

            if(plantsResult.value.isNotEmpty()){
                bEmptyPlants.postValue(false)
            }else{
                bEmptyPlants.postValue(true)
            }
        }else{
            bEmptyPlants.postValue(false)
            if(plantsResult is DomainResult.Failure){
                plantsResult.throwable?.printStackTrace()
            }
        }

    }

    fun navigateToSearch(){
        navManager.navigate(NavHomeFragmentDirections.actionToSearch(null))
    }

}