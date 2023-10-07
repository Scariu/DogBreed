package com.example.dogbreed.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.dogbreed.databinding.FragmentFirstBreedListBinding

class FirstFragmentBreedList : Fragment() {
    private lateinit var binding: FragmentFirstBreedListBinding
    private val viewModel: DogBreedViewModel by activityViewModels()
    private val adapter = AdapterItemBreed()
    private var isListShown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBreedListBinding.inflate(layoutInflater)
        if (!isListShown) {
            viewModel.getDataAllDogBreeds()
            isListShown = true
        }
        initAdapter()
        initListener()
        return binding.root
    }

    private fun initListener() {
        binding.searchViewList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText.toString()
                searchDataBase(query)
                return true
            }
        })
    }

    private fun initAdapter() {
        viewModel.breedsLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        binding.recyclerViewBreed.adapter = adapter
    }

    private fun searchDataBase(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchDataBaseLiveData(searchQuery).observe(viewLifecycleOwner) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }
}





