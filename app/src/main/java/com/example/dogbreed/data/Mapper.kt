package com.example.dogbreed.data

import com.example.dogbreed.data.local.detail.DogBreedDetailEntity
import com.example.dogbreed.data.local.list.DogBreedEntity

fun String.transformToEntity(id: String): DogBreedDetailEntity = DogBreedDetailEntity(id, this) //Función de extención

fun String.transformToBreedEntity(): DogBreedEntity = DogBreedEntity(this) //Función de extención