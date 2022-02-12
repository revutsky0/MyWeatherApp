package com.example.myweatherapp.presenter.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var id: Long = 0L
    private val viewModel by lazy { ViewModelProvider(this)[DetailViewModel::class.java] }
    private val pressureUnits by lazy { this.getString(R.string.wind_speed_units) }
    private val windUnits by lazy { this.getString(R.string.pressure_units) }
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(PARAM_ID)) {
                id = it.getLong(PARAM_ID)
            } else {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWeather(id)
        viewModel.weather.observe(viewLifecycleOwner, {
            it?.let {
                with(binding) {
                    tvDetailsDayAndDate.text = "${it.dayOfWeek}, ${it.date}"
                    tvDetailsDayNightTemp.text =
                        "${it.dayTemp} / ${it.nightTemp}"
                    tvDetailsStatus.text = it.status
                    tvDetailsClouds.text = "${it.clouds}%"
                    tvDetailsHumidity.text = "${it.humidity}%"
                    tvDetailsPrecipitation.text = "${it.precipitation}%"
                    tvDetailsWind.text = "${it.wind} $windUnits"
                    tvDetailsPressure.text = "${it.pressure} $pressureUnits"
                    tvDetailsClouds
                }
            }
        })
    }

    companion object {

        const val PARAM_ID = "weather_id"

        @JvmStatic
        fun newInstance(id: Long) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putLong(PARAM_ID, id)
                }
            }
    }
}