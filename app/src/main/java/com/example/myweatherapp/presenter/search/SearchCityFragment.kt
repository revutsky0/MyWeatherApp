package com.example.myweatherapp.presenter.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentSearchCityBinding
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.presenter.weather.WeatherFragment


class SearchCityFragment : Fragment() {

    private lateinit var binding: FragmentSearchCityBinding
    private val viewModel by lazy { ViewModelProvider(this)[SearchViewModel::class.java] }
    private val listAdapter by lazy {
        ArrayAdapter<City>(
            requireContext(),
            android.R.layout.simple_list_item_1
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCityBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchCityList.adapter = listAdapter
        setOnClickListeners()
        setObservable()
    }

    private fun setOnClickListeners() {
        binding.searchButton.setOnClickListener {
            val cityName = binding.searchCityName.text.toString()
            viewModel.searchCity(cityName)
        }
    }

    private fun setObservable() {
        viewModel.cityList.observe(viewLifecycleOwner, {
            listAdapter.clear()
            listAdapter.addAll(it)
        })
    }

    fun launchWeatherFragment(city: City) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainActivityFCV,
                WeatherFragment.newInstance(city)
            ).commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchCityFragment()
    }
}