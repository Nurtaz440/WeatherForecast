package com.nurtaz.dev.weatherapp.ui.pages.nav.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.navigation.fragment.findNavController
import coil.load
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.data.local.assets.entities.ModelCategory
import com.nurtaz.dev.weatherapp.databinding.FragmentNavHomeBinding
import com.nurtaz.dev.weatherapp.ui.base.FragmentBase
import com.nurtaz.dev.weatherapp.ui.pages.nav.home.category.HomeCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavHomeFragment : FragmentBase(false) {
    private val mViewModel: NavHomeViewModel by viewModels()
    private lateinit var vBinding: FragmentNavHomeBinding

    private lateinit var mListAdapter: HomeCategoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            FragmentNavHomeBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.lifecycleOwner = this.viewLifecycleOwner

        mListAdapter = HomeCategoryAdapter {
            navigateToFiltered(it)
        }

        /*{
            Log.d("AdListFragment", "AdListAdapter: $it")
            navigateToDetail(it.key)
        }*/
        Log.d("AdListFragment", "onCraeted:")


        lifecycleScope.launch { withCreated {
//            val layoutManager = GridLayoutManager(requireContext(), 2)

//            vBinding.recyclerViewAdList.layoutManager = layoutManager
            vBinding.recyclerview.adapter = mListAdapter



            vBinding.homeWeatherCard.homeWeatherCardImage.load(R.drawable.pic_weather)
            vBinding.homeScannerCard.homeScannerCardImage1.load(R.drawable.pic_scan_1)
            vBinding.homeScannerCard.homeScannerCardImage2.load(R.drawable.pic_scan_2)
            vBinding.homeScannerCard.homeScannerCardImage3.load(R.drawable.pic_scan_3)

//            mViewModel.initData()
//            throw RuntimeException("Test Crash")

        } }
//        {
//
//
//
//        }

    }

    private fun navigateToFiltered(category: ModelCategory) {
        Log.d("NavHomeFragment", "navigateToFiltered: $category")
        findNavController().navigate(NavHomeFragmentDirections.actionToFiltered(category.q, category.title, category.filterForEdible))
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        mViewModel.mCategoryList.observe(viewLifecycleOwner) {
            mListAdapter.submitList(it)

        }
    }
}