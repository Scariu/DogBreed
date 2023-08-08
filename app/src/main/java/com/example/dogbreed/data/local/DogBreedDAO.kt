package com.example.dogbreed.data.local

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface DogBreedDAO {
    @Insert
    suspend fun insertDogBreed(dogBreedEntity: DogBreedEntity)

    @Query("SELECT * FROM tabla_dogbreed order by breed ASC")
    fun getDogBreeds(): LiveData<List<DogBreedEntity>>
}