package com.nurtaz.dev.weatherapp.ui.pages.plant.filtered

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantSearchEntryEntity
import com.nurtaz.dev.weatherapp.domain.plant.usecases.GetPlantsByFilterUseCase
import com.nurtaz.dev.weatherapp.ui.base.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class PlantsFilteredViewModel  @Inject constructor(
    private val plantsByFilterUseCase: GetPlantsByFilterUseCase
): ViewModelBase() {

    var bTitle=  MutableLiveData<String>()
    var mFilterForEdible: Boolean = false
    var mQuery : String = ""

    fun initArgs(args: PlantsFilteredFragmentArgs) {
        mQuery = args.q?:""
        mFilterForEdible = args.filterForEdible
        bTitle.postValue(args.name?:"")
    }
    /*    fun setQ(filterForEdible : Boolean, q: String){
            mQuery = q
            mFilterForEdible = filterForEdible
        }*/

    fun loadSearch(): Flow<PagingData<PlantSearchEntryEntity>> {
        var result = plantsByFilterUseCase.invoke(mFilterForEdible, mQuery)
        return result.cachedIn(viewModelScope)
        /* if(result is DomainResult.Success){
            var pagingData =
             return pagingData

         }else {

         }
         return  null*/


    }
}