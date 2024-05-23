package com.nurtaz.dev.weatherapp.ui.pages.nav.plants.recommended

import androidx.recyclerview.widget.DiffUtil

data class ModelPlant(var id: Int){

    companion object {
        var diffCallback: DiffUtil.ItemCallback<ModelPlant> =
            object : DiffUtil.ItemCallback<ModelPlant>() {

                override fun areItemsTheSame(
                    oldItem: ModelPlant,
                    newItem: ModelPlant
                ): Boolean {
                    return oldItem.id == newItem.id                }

                override fun areContentsTheSame(
                    oldItem: ModelPlant,
                    newItem: ModelPlant
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

}