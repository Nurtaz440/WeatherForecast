package com.nurtaz.dev.weatherapp.ui.pages.nav.cure

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.databinding.FragmentNavCureBinding
import com.nurtaz.dev.weatherapp.tools.livedata.LiveDataObserver
import com.nurtaz.dev.weatherapp.ui.base.FragmentBase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavCureFragment: FragmentBase(false) {
    private val mViewModel: NavCureViewModel by viewModels()
    private lateinit var vBinding: FragmentNavCureBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding =
            FragmentNavCureBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        mViewModel.mEventGuidance.observe(viewLifecycleOwner, LiveDataObserver{
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(it))
            startActivity(intent)
        })

    }
}