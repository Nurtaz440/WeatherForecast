package com.nurtaz.dev.weatherapp.domain.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelCache
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import java.io.InputStream

class CustomImageSizeUrlLoader(
    concreteLoader: ModelLoader<GlideUrl, InputStream>,
    modelCache: ModelCache<CustomImageSizeModel, GlideUrl>
): BaseGlideUrlLoader<CustomImageSizeModel>(concreteLoader, modelCache) {
    override fun handles(model: CustomImageSizeModel): Boolean {
        return true
    }

    override fun getUrl(
        model: CustomImageSizeModel?,
        width: Int,
        height: Int,
        options: Options?
    ): String? {
        return model?.requestCustomSizeUrl(width, height);
    }
}