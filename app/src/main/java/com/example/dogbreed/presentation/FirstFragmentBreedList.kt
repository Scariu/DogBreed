package com.example.dogbreed.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dogbreed.databinding.FragmentFirstBreedListBinding

class FirstFragmentBreedList : Fragment() {
    private lateinit var binding: FragmentFirstBreedListBinding
    private val viewModel: DogBreedViewModel by activityViewModels()
    private val adapter = AdapterItemBreed()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBreedListBinding.inflate(layoutInflater)
        initListener()
        initAdapter()
        return binding.root
    }


    private fun initListener() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if (query != null) {
                    viewModel.searchDataBase(query)
                    searchDataBase(query)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun initAdapter() {
        binding.recyclerViewBreed.adapter = adapter
        viewModel.breedsLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

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






