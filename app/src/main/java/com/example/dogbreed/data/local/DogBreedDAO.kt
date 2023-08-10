package com.example.dogbreed.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogbreed.data.local.detail.DogBreedDetailEntity
import com.example.dogbreed.data.local.list.DogBreedEntity

@Dao
interface DogBreedDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreed(dogBreedEntity: DogBreedEntity)

    @Query("SELECT * FROM tabla_dogbreed order by breed ASC")
    fun getDogBreeds(): LiveData<List<DogBreedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreedDetail(dogBreedDetailEntity: DogBreedDetailEntity)

    /*@Query("SELECT * FROM tabla_dogbreed order by breed ASC")
    fun getDogBreedsDetails(): LiveData<List<DogBreedEntity>>*/
}