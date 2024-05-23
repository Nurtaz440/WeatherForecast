package com.nurtaz.dev.weatherapp.ui.res

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.nurtaz.dev.weatherapp.R

object ResourceBindingAdapter {
    @BindingAdapter("app:bindCoilSrc")
    @JvmStatic
    fun setBindCoilSrc(view: ImageView, src: Uri?) {
        // Important to break potential infinite loops.
//        info { "loadinhg $src" }
        if(src!=null){
            view.load(src) //.into(view)
        }else{
            view.load(R.drawable.taru_default)
        }

    }

    @BindingAdapter("app:bindCoilSrc")
    @JvmStatic
    fun setBindCoilSrc(view: ImageView, src: Int?) {
        // Important to break potential infinite loops.
//        info { "loadinhg $src" }
//        src?.let {
//            view.load(it) //.into(view)
//        }
        if(src!=null){
            view.load(src) //.into(view)
        }else{
            view.load(R.drawable.taru_default)
        }
    }

    @BindingAdapter("app:bindCoilSrc")
    @JvmStatic
    fun setBindCoilSrc(view: ImageView, src: String?) {
        // Important to break potential infinite loops.
//        info { "loadinhg $src" }
//        src?.let {
//            view.load(it) //.into(view)
//        }
        if(src!=null){
            view.load(src) //.into(view)
        }else{
            view.load(R.drawable.taru_default)
        }
    }
}