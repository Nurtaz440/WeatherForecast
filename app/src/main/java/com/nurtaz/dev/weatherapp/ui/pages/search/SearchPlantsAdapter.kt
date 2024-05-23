package com.nurtaz.dev.weatherapp.ui.pages.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.databinding.SearchPlantsItemBinding
import com.nurtaz.dev.weatherapp.ui.pages.nav.plants.recommended.ModelPlant

class SearchPlantsAdapter( val mOnClick: (ModelPlant) -> Unit) : ListAdapter<ModelPlant, SearchPlantsAdapter.ItemViewHolder>(
    ModelPlant.diffCallback) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return viewHolder(parent, this)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(var binding: SearchPlantsItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun bind(plant: ModelPlant) {
            binding.searchPlantsItemImage.load(R.drawable.pic_tool_category)
            binding.bOnClick =  this
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            var posi = absoluteAdapterPosition
            mOnClick(getItem(posi))
        }


    }

    companion object {
        fun viewHolder(parent: ViewGroup, adapter: SearchPlantsAdapter): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SearchPlantsItemBinding.inflate(layoutInflater, parent, false)
            return adapter.ItemViewHolder(binding)
        }
    }
}