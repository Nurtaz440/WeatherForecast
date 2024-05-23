package com.nurtaz.dev.weatherapp.ui.pages.plant.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantRecentSearchEntity
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantSearchEntryEntity
import com.nurtaz.dev.weatherapp.domain.plant.usecases.GetPlantRecentSearchByQueryUseCase
import com.nurtaz.dev.weatherapp.domain.plant.usecases.GetPlantsByQueryUseCase
import com.nurtaz.dev.weatherapp.ui.base.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
    private val plantsByQueryUseCase: GetPlantsByQueryUseCase,
    private val recentPlantByQueryUseCase: GetPlantRecentSearchByQueryUseCase
): ViewModelBase() {

    var bIsButtonEnabled = MutableLiveData(false)
    var bIsProgress= MutableLiveData(false)
    var mQuery : String = ""

    fun initArgs(args: SearchFragmentArgs) {
        mQuery = args.q?:""
    }
    fun setQ(q: String){
        mQuery = q
    }

    fun setProgress(progress: Boolean) {
//        TODO("Not yet implemented")
        bIsProgress.postValue(progress)
    }


    fun loadSearch(): Flow<PagingData<PlantSearchEntryEntity>> {
        var result = plantsByQueryUseCase.invoke(mQuery)
        return result.cachedIn(viewModelScope)
        /* if(result is DomainResult.Success){
            var pagingData =
             return pagingData

         }else {

         }
         return  null*/


    }

    fun loadRecentSearch(q:String?): Flow<PagingData<PlantRecentSearchEntity>> {
        Log.d("SearchViewModel", "loadRecentSearch: ${q?.length} ${q}")
        var result = recentPlantByQueryUseCase.invoke(q)
        return result.cachedIn(viewModelScope)
    }


}