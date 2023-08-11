package com.example.dogbreed.data

import com.example.dogbreed.data.local.detail.DogBreedDetailEntity

fun String.transformToEntity(id: String): DogBreedDetailEntity = DogBreedDetailEntity(id, this) //Función de extención