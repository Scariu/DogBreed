package com.example.dogbreed.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dogbreed.R
import com.example.dogbreed.databinding.FragmentFirstBreedListBinding
import com.example.dogbreed.databinding.FragmentSecondDetalleBinding

private const val ARG_PARAM1 = "id"



class SecondFragmentDetalle : Fragment() {
    lateinit var binding: FragmentSecondDetalleBinding
    private val viewModel: DogBreedViewModel by activityViewModels()
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondDetalleBinding.inflate(layoutInflater)
        viewModel.getDataAllDogBreedDetails(param1.toString())
        return binding.root
    }
}