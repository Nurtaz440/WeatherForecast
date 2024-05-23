package com.nurtaz.dev.weatherapp.ui.pages.nav.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nurtaz.dev.weatherapp.data.local.assets.entities.ModelCategory
import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherCurrentRoomEntity
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.plant.usecases.GetPlantCategoriesUseCase
import com.nurtaz.dev.weatherapp.domain.weather.usecase.GetWeatherForecastUseCase
import com.nurtaz.dev.weatherapp.domain.weather.usecase.GetWeatherUseCase
import com.nurtaz.dev.weatherapp.ui.base.ViewModelBase
import com.nurtaz.dev.weatherapp.ui.nav.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class NavHomeViewModel @Inject constructor(private val navManager: NavManager,
                                                    private val getPlantCategoriesUseCase: GetPlantCategoriesUseCase,
                                                    private val getWeatherUseCase: GetWeatherUseCase,
                                                    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
): ViewModelBase(){
    //    private val _mEventCategoryList = MutableLiveData<LiveDataEvent<List<ModelCategory>>>()
    val mCategoryList=  MutableLiveData<List<ModelCategory>>()
    //= _mEventCategoryList

    val bCurrentWeather = MutableLiveData<WeatherCurrentRoomEntity>()
    init {

        initData()
    }

    private fun initData(){
        getWeather()
        viewModelScope.launch {
            getPlantCategoriesUseCase().also {
                when(it) {
                    is DomainResult.Success -> {
                        Log.d("TAG", "initList: ${it.value}")
                        it.value.let { list ->
                            mCategoryList.postValue(list)
                        }
                        /*mCurrentList = it.value.items
                        _mEventAds.postValue(LiveDataEvent(it.value.items))*/
                    }

                    is DomainResult.Failure -> {
                        // _mEventAds.postValue(LiveDataEvent(it.value.items))

                        if(it.throwable!=null){
                            it.throwable.printStackTrace()
                        }
                        Log.d("AdListViewModel", "getList: failure ${it.throwable}")

                    }
                }
            }
        }
    }
    private fun getWeather() {

        viewModelScope.launch {

            getWeatherUseCase().also {


                when(it) {
                    is DomainResult.Success -> {
                        Log.d("TAG", "initList: ${it.value}")
                        bCurrentWeather.postValue(it.value!!)
                        /*mCurrentList = it.value.items
                        _mEventAds.postValue(LiveDataEvent(it.value.items))*/
                    }

                    is DomainResult.Failure -> {
                        // _mEventAds.postValue(LiveDataEvent(it.value.items))

                        if(it.throwable!=null){
                            it.throwable.printStackTrace()
                        }
                        Log.d("AdListViewModel", "getList: failure ${it.throwable}")

                    }
                }

            }

            /*getWeatherForecastUseCase().also {


                when(it) {
                    is DomainResult.Success -> {
                        Log.d("TAG", "F: initList: ${it.value}")
                        *//*mCurrentList = it.value.items
                        _mEventAds.postValue(LiveDataEvent(it.value.items))*//*
                    }

                    is DomainResult.Failure -> {
                        // _mEventAds.postValue(LiveDataEvent(it.value.items))

                        if(it.throwable!=null){
                            it.throwable.printStackTrace()
                        }
                        Log.d("AdListViewModel", "F: getList: failure ${it.throwable}")

                    }
                }

            }*/

        }
    }

    fun navigateToScan(){
        navManager.navigate(NavHomeFragmentDirections.actionGlobalToScan())
    }

    fun navigateToWeather(){
        navManager.navigate(NavHomeFragmentDirections.actionGlobalToWeather())
    }

    fun navigateToSearch(){
        navManager.navigate(NavHomeFragmentDirections.actionToSearch(null))
    }
}