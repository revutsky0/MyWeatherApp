package com.example.myweatherapp.presenter.search

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentSearchCityBinding
import com.example.myweatherapp.domain.models.City
import com.example.myweatherapp.presenter.main.MainActivity
import com.example.myweatherapp.presenter.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCityFragment : Fragment(), LocationListener {

    private var _binding: FragmentSearchCityBinding? = null
    private val binding: FragmentSearchCityBinding
        get() = _binding!!
    private val viewModel by viewModels<SearchViewModel>()
    private val listAdapter by lazy {
        ArrayAdapter<City>(
            requireContext(),
            R.layout.search_city_list_item
        )
    }
    private val manager: LocationManager by lazy {
        getSystemService(
            requireContext(),
            LocationManager::class.java
        ) as LocationManager
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getLocation()
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCityBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchCityList.adapter = listAdapter
        if (viewModel.needToGetLocationOnStartFragment()) {
            getLocation()
        }
        setOnClickListeners()
        setObservable()
    }

    private fun setOnClickListeners() = with(binding) {
        searchButton.setOnClickListener {
            val cityName = binding.searchCityName.text.toString()
            if (cityName.isEmpty()) {
                sendToast("Строка поиска пустая!")
            } else {
                viewModel.searchCity(cityName)
            }
        }
        searchCityList.setOnItemClickListener { _, _, position, _ ->
            val city = listAdapter.getItem(position) ?: return@setOnItemClickListener
            launchWeatherFragment(city)
        }
        locationButton.setOnClickListener {
            getLocation()
        }
    }


    private fun setObservable() {
        viewModel.cityList.observe(viewLifecycleOwner, {
            if (it.size == 1) {
                launchWeatherFragment(it[0])
            }
            listAdapter.clear()
            listAdapter.addAll(it)
        })
        viewModel.cityNotFound.observe(viewLifecycleOwner, {
            Toast.makeText(context, getString(R.string.city_not_found), Toast.LENGTH_LONG).show()
        })
    }

    private fun launchWeatherFragment(city: City) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(
                R.id.mainActivityFCV,
                WeatherFragment.newInstance(city)
            ).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sendToast(text: String) =
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()


    // region Location Listener methods

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                10,
                0f,
                this
            )
            log("LISTENER START")
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    override fun onLocationChanged(location: Location) {
        viewModel.searchCity(location)
        log("lat = ${location.latitude}, lon = ${location.longitude}")
        manager.removeUpdates(this)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        //
    }

    // endregion

    private fun log(text: String) = Log.d("MAIN", text)

    private fun toast(text: String) =
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()

    companion object {
        @JvmStatic
        fun newInstance() = SearchCityFragment()
    }

}