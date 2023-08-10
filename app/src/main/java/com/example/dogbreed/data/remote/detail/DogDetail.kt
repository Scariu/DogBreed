package com.example.dogbreed.data.remote.detail

//Para Secondfragment detalle se crea esta data class ya que la otra class tiene aimpoints distintos, la api tiene distinto formato
data class DogDetail(
    val message: List<String>,
    val status: String
)