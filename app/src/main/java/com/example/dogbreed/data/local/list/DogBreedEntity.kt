package com.example.dogbreed.data.local.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_dogbreed")
data class DogBreedEntity(@PrimaryKey val breed: String)