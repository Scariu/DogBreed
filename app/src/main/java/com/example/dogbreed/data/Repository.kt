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

    suspend fun getDogDetail(id: String) {
        val response = dogBreedAPI.getDetailDogData(id)
        if (response.isSuccessful) {
            response.body()!!.message.forEach {
                val dogDetailEntity = DogBreedDetailEntity(id, it)
                dogBreedDAO.insertDogBreedDetail(dogDetailEntity)
            }
        } else {
            Log.e("repositorio", response.errorBody().toString())
        }
    }
}
