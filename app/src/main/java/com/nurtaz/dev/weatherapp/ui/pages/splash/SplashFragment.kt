package com.nurtaz.dev.weatherapp.ui.pages.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nurtaz.dev.weatherapp.databinding.SplashFragmentBinding
import com.nurtaz.dev.weatherapp.ui.base.FragmentBase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment : FragmentBase(false) {

    private lateinit var vBinding: SplashFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            SplashFragmentBinding.inflate(inflater, container, false).apply {
//                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            delay(timeMillis = 500)
            navigateToHome()

        }
    }

    private fun navigateToHome() {
        var navCtrl = findNavController()
//            navCtrl.popBackStack()
//        navCtrl.navigate(LauncherFragmentDirections.)
        navCtrl.navigate(SplashFragmentDirections.navigateToHome())
    }

}