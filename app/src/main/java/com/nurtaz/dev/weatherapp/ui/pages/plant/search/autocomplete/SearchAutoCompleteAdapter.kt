package com.nurtaz.dev.weatherapp.ui.pages.plant.search.autocomplete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.databinding.SearchAutocompleteItemBinding
import com.nurtaz.dev.weatherapp.ui.pages.nav.plants.recent.ModelRecent

class SearchAutoCompleteAdapter ( val mOnClick: (ModelRecent) -> Unit) : ListAdapter<ModelRecent, SearchAutoCompleteAdapter.ItemViewHolder>(ModelRecent.diffCallback) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return viewHolder(parent, this)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(var binding: SearchAutocompleteItemBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        fun bind(plant: ModelRecent) {
            binding.searchAutocompleteItemImage.load(R.drawable.pic_tool_category)
            binding.bOnClick =  this
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            var posi = adapterPosition
            mOnClick(getItem(posi))
        }


    }

    companion object {
        fun viewHolder(parent: ViewGroup, adapter: SearchAutoCompleteAdapter): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SearchAutocompleteItemBinding.inflate(layoutInflater, parent, false)
            return adapter.ItemViewHolder(binding)
        }
    }
}