package com.example.dogbreed.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DogBreedEntity::class], version = 1)
abstract class DogBreedDataBase : RoomDatabase() {
    abstract fun getBreedDAO(): DogBreedDAO

    companion object {
        @Volatile
        private var INSTANCE: DogBreedDataBase? = null

        fun getDatabase(context: Context): DogBreedDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogBreedDataBase::class.java,
                    "breeds_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
