package com.example.dogbreed.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface DogBreedAPI {
    @GET("breeds/list/all")
    suspend fun getData(): Response<DogBreed>
}