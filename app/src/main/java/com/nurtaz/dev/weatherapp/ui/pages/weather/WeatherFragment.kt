package com.nurtaz.dev.weatherapp.ui.pages.weather

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.google.android.material.color.MaterialColors
import com.nurtaz.dev.weatherapp.R
import com.nurtaz.dev.weatherapp.data.local.db.weather.ForecastEntryEntity
import com.nurtaz.dev.weatherapp.databinding.FragmentWeatherBinding
import com.nurtaz.dev.weatherapp.domain.chart.pmchart.LineChartBinderHelper
import com.nurtaz.dev.weatherapp.domain.chart.pmchart.axis.MpChartTimestampAxisFormatter
import com.nurtaz.dev.weatherapp.domain.chart.pmchart.entities.LineChartDataSetConfig
import com.nurtaz.dev.weatherapp.tools.livedata.LiveDataObserver
import com.nurtaz.dev.weatherapp.ui.base.FragmentBase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : FragmentBase(true) {

    private var colorSurface: Int = Color.WHITE
    private var colorOnSurface: Int = Color.BLACK


    private var mLineChartBinderHelper: LineChartBinderHelper = LineChartBinderHelper()
    private val mViewModel: WeatherViewModel by viewModels()
    private lateinit var vBinding: FragmentWeatherBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vBinding =
            FragmentWeatherBinding.inflate(inflater, container, false).apply {
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
        lifecycleScope.launchWhenCreated {
            vBinding.weatherImage.load(R.drawable.pic_weather)
            colors()
            setupChart()


            mViewModel.initData()

        }
    }

    override fun setupViewModelObservers() {
        super.setupViewModelObservers()
        mViewModel.mEventForecastEntries.observe(viewLifecycleOwner, LiveDataObserver {
            bindToChart(it)
        })
    }

    private fun setupChart() {

        var vChart = vBinding.weatherFragmentForecastChart
        vChart.apply {
            //Set shadow
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//            renderer.paintRender.setShadowLayer(3F, 5F, 3F, Color.Gray.toArgb());
//            setViewPortOffsets(0f, 0f, 0f, 0f);


            applyAxis(this, )
            legend.isEnabled = true
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.setDrawInside(false)
            legend.textColor = colorOnSurface

            setBackgroundColor(Color.TRANSPARENT)
            setDrawGridBackground(false)
            setDrawBorders(false)
//            setDrawMarkers(true)


            description.isEnabled = false
            axisLeft.setDrawLabels(true)
//           axisRight.setDrawLabels(false)
            axisLeft.setDrawGridLines(true)
        }
        var colorHigh = ContextCompat.getColor(requireContext(), R.color.chart_high)
        var colorLow = ContextCompat.getColor(requireContext(), R.color.chart_low)
        var configs = mutableListOf(
            LineChartDataSetConfig(colorHigh, "Max", colorOnSurface),
            LineChartDataSetConfig(colorLow, "Min", colorOnSurface))

        /*val gradient = LinearGradient(
            0f, 600f, 0f, 0f,
            colorHigh,
            colorLow,
            Shader.TileMode.CLAMP)

        val paint = vChart.renderer.paintRender
        paint.setShader(gradient)*/

        mLineChartBinderHelper.prepareDataSets(vBinding.weatherFragmentForecastChart, configs)
        vChart.invalidate()


    }

    private fun colors() {
        colorOnSurface = MaterialColors.getColor(
            requireContext(),
            com.google.android.material.R.attr.colorOnSurface,
            ContextCompat.getColor(requireContext(), R.color.color_on_surface_54)
        )
        colorSurface = MaterialColors.getColor(
            requireContext(),
            com.google.android.material.R.attr.colorSurface,
            ContextCompat.getColor(requireContext(), R.color.color_back_54)
        )
    }

    private fun bindToChart(entities: List<ForecastEntryEntity>) {
        //TODO("Not yet implemented")

        var values = MutableList<FloatArray>(entities.size, init = {index -> floatArrayOf(entities[index].attrs.tempMax, entities[index].attrs.tempMin) })
        var dts = MutableList<Int>(entities.size, init = {index -> entities[index].dt })

        mLineChartBinderHelper.update(vBinding.weatherFragmentForecastChart, dts, values)


    }

    private fun applyAxis(lineChart: LineChart) {
//        ContextCompat.getColorStateList(requireContext(), R.color.colorOnSurface)


        lineChart.apply {
            //Formatting

            xAxis.valueFormatter = MpChartTimestampAxisFormatter()
//            xAxis.granularity = 1f
            xAxis.labelCount = 4
            xAxis.setDrawAxisLine(false)
            xAxis.setDrawGridLines(false)
            xAxis.setDrawLabels(true)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.textColor = colorOnSurface

            axisRight.setDrawZeroLine(false)
            axisRight.isEnabled = false
            axisRight.textColor = colorOnSurface

            axisLeft.setDrawZeroLine(false)
            axisLeft.isEnabled = true
            axisLeft.setDrawAxisLine(false)
//           axisRight.setDrawAxisLine(false)
            axisLeft.setDrawLabels(false)
//           axisRight.setDrawLabels(false)
            axisLeft.setDrawGridLines(false)
//           axisRight.setDrawGridLines(false)
//            axisLeft.axisMinimum = 0f
            axisLeft.textColor = colorOnSurface
        }

    }
}