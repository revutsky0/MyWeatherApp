package com.example.myweatherapp.presenter.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myweatherapp.R
import com.example.myweatherapp.databinding.FragmentMainBinding
import com.example.myweatherapp.presenter.detail.DetailFragment

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val adapter: WeeklyAdapter by lazy { WeeklyAdapter() }
    private var currentBackground = R.drawable.clouds_bg
    private var id = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {
                    //
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        setObservable()
    }

    private fun setOnClickListeners() {
        with(binding) {
            adapter.onWeatherDailyItemClickListener =
                WeeklyAdapter.OnWeatherDailyItemClickListener {
                    launchDetailFragment(it)
                }
            rvWeeklyWeather.adapter = adapter
            ibFindCity.setOnClickListener {
                viewModel.findCity(etCity.text.toString())
            }
            clCurrentWeather.setOnClickListener {
                launchDetailFragment(id)
            }
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
}

