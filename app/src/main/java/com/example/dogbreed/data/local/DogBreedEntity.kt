package com.example.dogbreed.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_dogbreed")
class DogBreedEntity(@PrimaryKey val breed: String)