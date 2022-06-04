package com.example.myweatherapp.presenter.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myweatherapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplashBinding.inflate(
            inflater, container, false
        )
        viewModel.lastCity.observe(viewLifecycleOwner) {
            if (it == null) {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToSearchCityFragment()
                )
            } else {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToWeatherFragment(it)
                )
            }
        }
        viewModel.load()
        return binding.root
    }

}