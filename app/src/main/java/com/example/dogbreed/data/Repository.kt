package com.example.dogbreed.data

import androidx.lifecycle.LiveData
import com.example.dogbreed.data.local.DogBreedDAO
import com.example.dogbreed.data.local.DogBreedEntity
import com.example.dogbreed.data.remote.DogBreedAPI

class Repository(private val dogBreedAPI: DogBreedAPI, private val dogBreedDAO: DogBreedDAO) {
    fun getBreedsEntity(): LiveData<List<DogBreedEntity>> = dogBreedDAO.getDogBreeds()
    suspend fun getBreeds() {
        val response = dogBreedAPI.getData() // Aqui llegan los datos
        if (response.isSuccessful) { //Evalua si llegaron los datos
            val message = response.body()!!.message // Solo obtiene el mensaje, no tiene status
            val keys = message.keys
            keys.forEach {
                val dogBreedEntity = DogBreedEntity(it)
                dogBreedDAO.insertDogBreed(dogBreedEntity)
            }
        }
    }
}