package com.nurtaz.dev.weatherapp.ui.pages.nav.plants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.databinding.FragmentNavPlantsBinding
import com.nurtaz.dev.weatherapp.tools.livedata.LiveDataObserver
import com.nurtaz.dev.weatherapp.ui.base.FragmentBase
import com.nurtaz.dev.weatherapp.ui.pages.nav.plants.recent.RecentPlantsAdapter
import com.nurtaz.dev.weatherapp.ui.pages.nav.plants.recent.RecentSearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavPlantsFragment: FragmentBase(false) {
    private val mViewModel: NavPlantsViewModel by viewModels()
    private lateinit var vBinding: FragmentNavPlantsBinding
    private lateinit var mListAdapterRecent: RecentSearchAdapter
    private lateinit var mListAdapter: RecentPlantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding =
            FragmentNavPlantsBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vBinding.lifecycleOwner = this.viewLifecycleOwner

        mListAdapterRecent = RecentSearchAdapter(){
            navigateToSearch(it.q)
        }
        mListAdapter = RecentPlantsAdapter() {
                modelPlant ->  navigateToDetail(modelPlant.id)
        }
        /*{
            Log.d("AdListFragment", "AdListAdapter: $it")
            navigateToDetail(it.key)
        }*/
        Log.d("AdListFragment", "onCraeted:")


        lifecycleScope.launchWhenCreated {
//            val layoutManager = GridLayoutManager(requireContext(), 2)

//            vBinding.recyclerViewAdList.layoutManager = layoutManager
            vBinding.recyclerviewHorizontal.adapter = mListAdapterRecent


            /* layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                 override fun getSpanSize(position: Int): Int {
                     return mListAdapter.getItemViewType(position)
                 }
             }*/
            /*var list = mutableListOf(
                ModelRecent(0), ModelRecent(1), ModelRecent(2),
                ModelRecent(3), ModelRecent(4), ModelRecent(5),
                ModelRecent(6), ModelRecent(7), ModelRecent(8), ModelRecent(9)
            )*/


//            mListAdapterRecent.submitList(list)

            vBinding.recyclerview.adapter = mListAdapter
            /*  var list2 = mutableListOf(
                  ModelPlant(0), ModelPlant(1), ModelPlant(2),
                  ModelPlant(3), ModelPlant(4), ModelPlant(5),
                  ModelPlant(6), ModelPlant(7), ModelPlant(8), ModelPlant(9)
              )


              mListAdapter.submitList(list2)*/

            //            throw RuntimeException("Test Crash")

            /*mViewModel.mListStateParcel?.let {

                Log.d("AdListAdapter", "listStae: ${it.toString()}")
//                layoutManager.onRestoreInstanceState(it)
//                mViewModel.saveListState(null)
            }*/
            mViewModel.initList()

        }
    }

    private fun navigateToSearch(q: String) {
        findNavController().navigate(NavPlantsFragmentDirections.actionToSearch(q))
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        mViewModel.mEventRecentSearchList.observe(viewLifecycleOwner, LiveDataObserver{
            mListAdapterRecent.submitList(it)

        })
        mViewModel.mEventRecentPlantList.observe(viewLifecycleOwner, LiveDataObserver{
            mListAdapter.submitList(it)

        })
    }

    private fun navigateToDetail(id: Int) {
        findNavController().navigate(NavPlantsFragmentDirections.actionToDetail(id))
        // TODO uncomment findNavController().navigate(NavPlantsFragmentDirections.actionToDetail(12))
    }
}