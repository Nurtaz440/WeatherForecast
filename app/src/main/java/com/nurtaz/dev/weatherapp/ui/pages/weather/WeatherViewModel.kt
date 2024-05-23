package com.nurtaz.dev.weatherapp.ui.pages.weather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nurtaz.dev.weatherapp.data.local.db.weather.ForecastEntryEntity
import com.nurtaz.dev.weatherapp.data.local.db.weather.WeatherCurrentRoomEntity
import com.nurtaz.dev.weatherapp.domain.base.DomainResult
import com.nurtaz.dev.weatherapp.domain.weather.usecase.GetWeatherForecastUseCase
import com.nurtaz.dev.weatherapp.domain.weather.usecase.GetWeatherUseCase
import com.nurtaz.dev.weatherapp.tools.livedata.LiveDataEvent
import com.nurtaz.dev.weatherapp.ui.base.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WeatherViewModel  @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase

): ViewModelBase(){

    val bCurrentWeather = MutableLiveData<WeatherCurrentRoomEntity>()
    private val _mEventForecastEntries = MutableLiveData<LiveDataEvent<List<ForecastEntryEntity>>>()
    val mEventForecastEntries: LiveData<LiveDataEvent<List<ForecastEntryEntity>>> = _mEventForecastEntries

    var bIsProgress = MutableLiveData(false)
    fun initData() {
        getWeather()
    }

    private fun getWeather() {

        viewModelScope.launch {
            bIsProgress.postValue(true)
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

            getWeatherForecastUseCase().also {


                when(it) {
                    is DomainResult.Success -> {
                        Log.d("TAG", "F: initList: ${it.value}")
                        _mEventForecastEntries.postValue(LiveDataEvent(it.value.entries))

                        /*mCurrentList = it.value.items
                        _mEventAds.postValue(LiveDataEvent(it.value.items))*/
                    }

                    is DomainResult.Failure -> {
                        // _mEventAds.postValue(LiveDataEvent(it.value.items))

                        if(it.throwable!=null){
                            it.throwable.printStackTrace()
                        }
                        Log.d("AdListViewModel", "F: getList: failure ${it.throwable}")

                    }
                }

            }

            bIsProgress.postValue(false)

        }
    }
}