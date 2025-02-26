package com.nurtaz.dev.weatherapp.ui.pages.plant.filtered

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.databinding.FragmentPlantsFilteredBinding
import com.nurtaz.dev.weatherapp.ui.base.FragmentBase
import com.nurtaz.dev.weatherapp.ui.pages.plant.common.PlantsPagingAdapter
import com.nurtaz.dev.weatherapp.ui.pages.plant.search.autocomplete.SearchAutoCompleteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlantsFilteredFragment : FragmentBase(true) {

    private val args: PlantsFilteredFragmentArgs by navArgs()

    private val mViewModel: PlantsFilteredViewModel by viewModels()
    private lateinit var vBinding: FragmentPlantsFilteredBinding

    private lateinit var mListAdapterAutoComplete: SearchAutoCompleteAdapter

    //    private lateinit var mListAdapter: SearchPlantsAdapter
    private lateinit var mPagingAdapter: PlantsPagingAdapter
    private var plantsJob: Job? = null
    //    private var recentJob: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            FragmentPlantsFilteredBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.lifecycleOwner = this.viewLifecycleOwner
        vBinding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
            findNavController().popBackStack()
        }
        mListAdapterAutoComplete = SearchAutoCompleteAdapter() {

        }
        /*  mListAdapter = SearchPlantsAdapter() {
                  modelPlant ->
          //navigateToDetail(modelPlant.id)
          }*/

        lifecycleScope.launchWhenCreated {

            mViewModel.initArgs(args)

            initPagingAdapter()
            getPlantsSearchEntries()

            Log.d("SearchFragment: ", "callback: ")
            /*else{
                Log.d("SearchFragment: ", "callback: ")
                this.handleOnBackPressed()
            }*/
            // Handle the back button event
        }

    }

    private fun initPagingAdapter() {
        mPagingAdapter = PlantsPagingAdapter {
            navigateToDetail(it.plantId)
        }
        vBinding.recyclerview.adapter = mPagingAdapter

        mPagingAdapter.addLoadStateListener { loadState ->
            Log.d("SearchFragment", "loadState:  ${loadState}")
            /* binding.articleList.isVisible = loadState.refresh is LoadState.NotLoading
             binding.progress.isVisible = loadState.refresh is LoadState.Loading
             manageErrors(loadState)*/
        }
    }

    private fun getPlantsSearchEntries() {
        plantsJob?.cancel()
        plantsJob = lifecycleScope.launch {
            mViewModel.loadSearch().collectLatest { mPagingAdapter.submitData(it) }
            Log.d("SearchFragment", "getPlantsSearchEntries: job")

        }
    }

    private fun navigateToDetail(plantId: Int) {
        findNavController().navigate(PlantsFilteredFragmentDirections.navigateToPlantDetail(plantId))
    }
}