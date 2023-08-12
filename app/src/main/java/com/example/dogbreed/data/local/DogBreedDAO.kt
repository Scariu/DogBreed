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

    //Muestra todas las imagenes de 1 raza con el id
    @Query("SELECT * FROM table_detailbreed WHERE dogBreedDetail LIKE :id")
    fun getDogBreedsDetails(id: String): LiveData<List<DogBreedDetailEntity>>

    //para pruebas android
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDogBreed(dogBreedEntity: List<DogBreedEntity>)
}