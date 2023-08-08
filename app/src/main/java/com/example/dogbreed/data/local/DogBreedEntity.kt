package com.example.dogbreed.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_dogbreed")
class DogBreedEntity(val breed: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}