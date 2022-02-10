package com.example.myweatherapp.presenter.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentSearchCityBinding
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.presenter.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCityFragment : Fragment() {

    private lateinit var binding: FragmentSearchCityBinding
    private val viewModel by lazy { ViewModelProvider(this)[SearchViewModel::class.java] }
    private val listAdapter by lazy {
        ArrayAdapter<City>(
            requireContext(),
            R.layout.search_city_list_item
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
        binding.searchCityList.setOnItemClickListener { _, _, position, _ ->
            val city = listAdapter.getItem(position) ?: return@setOnItemClickListener
            launchWeatherFragment(city)
        }
    }

    private fun setObservable() {
        viewModel.cityList.observe(viewLifecycleOwner, {
            listAdapter.clear()
            listAdapter.addAll(it)
        })
        viewModel.cityNotFound.observe(viewLifecycleOwner, {
            Toast.makeText(context, getString(R.string.city_not_found), Toast.LENGTH_LONG).show()
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