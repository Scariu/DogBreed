package com.example.dogbreed.data.remote

import com.example.dogbreed.data.remote.detail.DogDetail
import com.example.dogbreed.data.remote.list.DogBreed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedAPI {
    @GET("breeds/list/all")
    suspend fun getData(): Response<DogBreed>

    @GET("breed/{id}/images")
    suspend fun getDetailDogData(@Path("id") id: String): Response<DogDetail>
}