package com.example.myweatherapp.presenter.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentWeatherBinding
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.presenter.detail.DetailFragment
import com.example.myweatherapp.presenter.search.SearchCityFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding: FragmentWeatherBinding
        get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels()
    private val adapter: WeeklyAdapter by lazy { WeeklyAdapter() }
    private var currentBackground = R.drawable.clouds_bg
    private var id = 0L
    private lateinit var city: City

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city = arguments?.getSerializable(PARAM_CITY) as City
        setObservable()
        setOnClickListeners()
        viewModel.loadCityWeather(city)
    }

    private fun setOnClickListeners() {
        with(binding) {
            adapter.onWeatherDailyItemClickListener =
                WeeklyAdapter.OnWeatherDailyItemClickListener {
                    launchDetailFragment(it)
                }
            rvWeeklyWeather.adapter = adapter
            clCurrentWeather.setOnClickListener {
                launchDetailFragment(id)
            }
            weatherSearchButton.setOnClickListener { launchSearchCityFragment() }
        }
    }

    private fun setObservable() {
        viewModel.weeklyWeather.observe(viewLifecycleOwner, {
            Log.d("MAIN", "UPDATE WEEKLY WEATHER")
            adapter.weeklyWeather = it
        })
        viewModel.currentWeather.observe(viewLifecycleOwner,
            {
                it?.let {
                    id = it.id
                    Log.d("MAIN", "UPDATE CURRENT WEATHER")
                    with(binding) {
                        cvCurrentWeather.visibility = View.VISIBLE
                        tvCityName.text = city.getLocalName()
                        tvCurrentTemp.text = it.currentTemp
                        tvWeatherStatus.text = it.status
                        val background = it.background
                        if (currentBackground != background) {
                            MainConstraintLayout.background = resources.getDrawable(background)
                            currentBackground = background
                        }
                    }
                }
            }
        )
        viewModel.currentDailyWeather.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvDayTemp.text = it.dayTemp
                binding.tvNightTemp.text = it.nightTemp
            }
        })
    }

    private fun launchDetailFragment(id: Long) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityFCV, DetailFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }

    private fun launchSearchCityFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityFCV, SearchCityFragment.newInstance())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val PARAM_CITY = "city"

        @JvmStatic
        fun newInstance(city: City) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PARAM_CITY, city)
                }
            }
    }


}

