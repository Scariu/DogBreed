package com.example.dogbreed.data.local.detail

import androidx.room.Entity
//Data class tiene implementado por defecto getter y setters, toString (no es necesario sobreescribir toString), equals y hashcode
@Entity(tableName = "table_detailbreed", primaryKeys = ["dogBreedDetail", "url"])
data class DogBreedDetailEntity(
    val dogBreedDetail: String,
    val url: String
)