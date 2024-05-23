package com.nurtaz.dev.weatherapp.ui.pages.slider

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nurtaz.dev.weatherapp.ui.base.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(

): ViewModelBase(){

    private var mImageList : List<String> = listOf()

    var bImages = MutableLiveData<List<ModelSliderImage>>()

    fun initArgs(args: SliderFragmentArgs) {
        mImageList = args.images.toList()

        viewModelScope.launch {
            bImages.postValue(mImageList.mapIndexed { index, s ->   ModelSliderImage(index, s) })
        }

    }

}