package com.example.dogbreed.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dogbreed.data.Repository
import com.example.dogbreed.data.local.DogBreedDAO
import com.example.dogbreed.data.local.DogBreedDataBase
import com.example.dogbreed.data.local.list.DogBreedEntity
import com.example.dogbreed.data.remote.DogBreedRetrofit
import kotlinx.coroutines.launch
import retrofit2.http.Query

class DogBreedViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    fun breedsLiveData() = repository.getBreedsEntity()
    fun detailsLiveData(id: String) = repository.getDogDetailsEntity(id)
    init {
        val api = DogBreedRetrofit.getDogBreedRetrofit()
        val dogBreedDataBase: DogBreedDAO = DogBreedDataBase.getDatabase(application).getBreedDAO()
        repository = Repository(api, dogBreedDataBase)
    }

    fun getDataAllDogBreeds() = viewModelScope.launch { repository.getBreeds() }

    fun getDataAllDogBreedDetails(id: String) =
        viewModelScope.launch { repository.getDogDetail(id) }

    fun searchDataBaseVM(searchQuery: String): LiveData<List<DogBreedEntity>>{
        return repository.searchDataBaseRepo(searchQuery)
    }
}
