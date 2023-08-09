package com.example.dogbreed.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dogbreed.databinding.FragmentFirstBreedListBinding

class FirstFragmentBreedList : Fragment() {
    private lateinit var binding: FragmentFirstBreedListBinding
    private val viewModel: DogBreedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBreedListBinding.inflate(layoutInflater)
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {
        viewModel.getDataAllDogBreeds()
        val adapter = AdapterItemBreed()
        binding.recyclerViewBreed.adapter = adapter
        viewModel.breedsLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}
