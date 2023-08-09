package com.example.dogbreed.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogBreedDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreed(dogBreedEntity: DogBreedEntity)

    @Query("SELECT * FROM tabla_dogbreed order by breed ASC")
    fun getDogBreeds(): LiveData<List<DogBreedEntity>>
}