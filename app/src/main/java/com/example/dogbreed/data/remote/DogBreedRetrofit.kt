package com.example.dogbreed.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogBreedRetrofit {
    companion object {
        private const val URL_BASE = "https://dog.ceo/api/"

        fun getDogRaceRetrofit(): DogBreedAPI {
            val mRetrofit = Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return mRetrofit.create(DogBreedAPI::class.java)
        }
    }
}