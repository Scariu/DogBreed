package com.example.dogbreed.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.dogbreed.data.local.DogBreedDAO
import com.example.dogbreed.data.local.detail.DogBreedDetailEntity
import com.example.dogbreed.data.local.list.DogBreedEntity
import com.example.dogbreed.data.remote.DogBreedAPI

//El repositorio es el administrador de los datos
class Repository(private val dogBreedAPI: DogBreedAPI, private val dogBreedDAO: DogBreedDAO) {
    fun getBreedsEntity(): LiveData<List<DogBreedEntity>> = dogBreedDAO.getDogBreeds()
    fun getDogDetailsEntity(id: String): LiveData<List<DogBreedDetailEntity>> =
        dogBreedDAO.getDogBreedsDetails(id)

    //Buscador
    fun searchDataBaseRepo(searchQuery: String): LiveData<List<DogBreedEntity>> = dogBreedDAO.searchDataBase(searchQuery)

    suspend fun getBreeds() {
        try {
            val response = dogBreedAPI.getData() // Aqui llegan los datos
            if (response.isSuccessful) { //Evalua si llegaron los datos
                val message = response.body()!!.message // Solo obtiene el mensaje, no tiene status
                val keys = message.keys
                keys.forEach {
                    val dogBreedEntity = it.transformToBreedEntity()// Transforma de String DogBreedEntity(breed) por medio de la función de extención
                    dogBreedDAO.insertDogBreed(dogBreedEntity)
                }
            }
        } catch (exception: Exception) {
            Log.e("catch", "")
        }
    }

    suspend fun getDogDetail(id: String) {
        try {
            val response = dogBreedAPI.getDetailDogData(id)
            if (response.isSuccessful) {
                response.body()!!.message.forEach {
                    val dogDetailEntity = it.transformToEntity(id)// Transforma de String a DetailEntity(url) por medio de la función de extención
                    dogBreedDAO.insertDogBreedDetail(dogDetailEntity)
                }
            } else {
                Log.e("repositorio", response.errorBody().toString())
            }

        } catch (exception: Exception) {
            Log.e("catch", "")
        }
    }
}