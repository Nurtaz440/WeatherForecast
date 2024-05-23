package com.nurtaz.dev.weatherapp.ui.pages.scan.result

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.nurtaz.dev.weatherapp.ui.base.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ScanResultViewModel @Inject constructor() : ViewModelBase() {

    var bImageUri = MutableLiveData<Uri>()
    var bKeywords = MutableLiveData<List<String>>()

    val bIsProgress = MutableLiveData(false)
    fun initArgs(args: ScanResultFragmentArgs) {
        bImageUri.postValue(Uri.parse(args.imageUri))
        bKeywords.postValue(args.keywords.toList().take(5))

    }




}