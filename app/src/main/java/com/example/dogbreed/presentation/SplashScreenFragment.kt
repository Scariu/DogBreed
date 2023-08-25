package com.example.dogbreed.presentation

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.dogbreed.R
import com.example.dogbreed.databinding.FragmentFirstBreedListBinding
import com.example.dogbreed.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {
    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        splashScreenTime()
        return binding.root
    }


    private fun splashScreenTime() {
        //Para configurar el tiempo
        viewLifecycleOwner.lifecycleScope.launch {
            delay(1500)
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerViewActivity, FirstFragmentBreedList())
                .commit()

        }
    }
}