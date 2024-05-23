package com.nurtaz.dev.weatherapp.ui.pages.detal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.ChipGroup
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.databinding.FragmentPlantDetailBinding
import com.nurtaz.dev.weatherapp.databinding.PlantDetailChipBinding
import com.nurtaz.dev.weatherapp.ui.base.FragmentBase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailFragment: FragmentBase(true) {

    private val args: PlantDetailFragmentArgs by navArgs()
    private val mViewModel: PlantDetailViewModel by viewModels()
    private lateinit var vBinding: FragmentPlantDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            FragmentPlantDetailBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.lifecycleOwner = this.viewLifecycleOwner

        lifecycleScope.launchWhenCreated {
            mViewModel.initArgs(args)
//            vBinding.plantDetailImage.load(R.drawable.pic_scan_1)
        }

    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        mViewModel.bKeywords.observe(viewLifecycleOwner) {
            addChips(vBinding.plantDetailSectionRegionChipGroup, it)
        }


    }



    private fun addChips(chipGroup: ChipGroup, keywords: List<String>){


        chipGroup.removeAllViews()
        keywords.forEach {

            val chip = PlantDetailChipBinding.inflate(
                LayoutInflater.from(requireContext()),
                chipGroup, false).root

            chip.text = it
            chipGroup.addView(chip)
        }
    }

}