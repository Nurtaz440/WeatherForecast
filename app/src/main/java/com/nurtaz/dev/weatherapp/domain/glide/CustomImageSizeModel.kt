package com.nurtaz.dev.weatherapp.domain.glide

import android.net.Uri
import com.github.piasy.biv.loader.glide.GlideModel

class CustomImageSizeModel: GlideModel {
    private var baseImageUrl: Uri? = null

    override fun setBaseImageUrl(baseImageUrl: Uri?) {
        this.baseImageUrl = baseImageUrl
    }

    fun requestCustomSizeUrl(width: Int, height: Int): String? {
        if (baseImageUrl != null) {
            return baseImageUrl!!
                .buildUpon()
                .appendQueryParameter("width", width.toString())
                .appendQueryParameter("height", height.toString())
                .build()
                .toString()
        }
        throw RuntimeException("You have to set the base image url first")
    }


}