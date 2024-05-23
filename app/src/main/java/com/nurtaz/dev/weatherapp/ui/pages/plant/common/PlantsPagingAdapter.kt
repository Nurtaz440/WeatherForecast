package com.nurtaz.dev.weatherapp.ui.pages.plant.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nurtaz.dev.weatherapp.data.local.db.plant.PlantSearchEntryEntity
import com.nurtaz.dev.weatherapp.databinding.SearchPlantsItemBinding

class PlantsPagingAdapter(val mOnClick: (PlantSearchEntryEntity) -> Unit) : PagingDataAdapter<PlantSearchEntryEntity, PlantsPagingAdapter.ItemViewHolder>(PlantSearchEntryEntity.diffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entry = getItem(position) ?: return
        holder.bind(entry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return viewHolder(parent, this)
    }

    inner class ItemViewHolder(var binding: SearchPlantsItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(plantSearchEntryEntity: PlantSearchEntryEntity) {
//            binding.searchPlantsItemImage.load(R.drawable.pic_tool_category)
            binding.bOnClick =  this
            binding.bModel = plantSearchEntryEntity
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            var posi = absoluteAdapterPosition
            getItem(posi)?.let { mOnClick(it) }
        }


    }
    companion object {
        fun viewHolder(parent: ViewGroup, adapter: PlantsPagingAdapter): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SearchPlantsItemBinding.inflate(layoutInflater, parent, false)
            return adapter.ItemViewHolder(binding)
        }
    }

}