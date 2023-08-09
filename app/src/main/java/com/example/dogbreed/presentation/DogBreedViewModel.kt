package com.example.dogbreed.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreed.data.Repository
import com.example.dogbreed.data.local.DogBreedDAO
import com.example.dogbreed.data.local.DogBreedDataBase
import com.example.dogbreed.data.remote.DogBreedRetrofit
import kotlinx.coroutines.launch

class DogBreedViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    fun breedsLiveData() = repository.getBreedsEntity()
    init {
        val api = DogBreedRetrofit.getDogBreedRetrofit()
        val dogBreedDataBase: DogBreedDAO = DogBreedDataBase.getDatabase(application).getBreedDAO()
        repository = Repository(api, dogBreedDataBase)
    }

    fun getDataAllDogBreeds() = viewModelScope.launch { repository.getBreeds() }
}