package com.nurtaz.dev.weatherapp.ui.pages.nav.abut

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.databinding.FragmentNavAboutBinding
import com.nurtaz.dev.weatherapp.ui.base.FragmentBase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavAboutFragment: FragmentBase(false) {

    private val mViewModel: NavAboutViewModel by viewModels()
    private lateinit var vBinding: FragmentNavAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vBinding =
            FragmentNavAboutBinding.inflate(inflater, container, false).apply {
                bViewModel = mViewModel
            }
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vBinding.lifecycleOwner = this.viewLifecycleOwner

        vBinding.buttonGithub.setOnClickListener() {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/nirajprakash/android-testing"))
            startActivity(intent)
        }
        vBinding.buttonJunkiesLabs.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://junkielabs.in"))
            startActivity(intent)
        }

        vBinding.buttonPrivacy.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://nirajprakash.github.io/documents/taru/privacy.html"))
            startActivity(intent)
        }
    }
}