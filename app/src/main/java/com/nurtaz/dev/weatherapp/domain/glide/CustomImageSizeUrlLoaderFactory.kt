package com.nurtaz.dev.weatherapp.domain.glide

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelCache
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import java.io.InputStream

class CustomImageSizeUrlLoaderFactory: ModelLoaderFactory<CustomImageSizeModel, InputStream> {
    private val modelCache: ModelCache<CustomImageSizeModel, GlideUrl> = ModelCache<CustomImageSizeModel, GlideUrl>(500)

    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<CustomImageSizeModel, InputStream> {
        val modelLoader = multiFactory.build(
            GlideUrl::class.java,
            InputStream::class.java
        )
        return CustomImageSizeUrlLoader(modelLoader, modelCache)
    }

    override fun teardown() {}
}