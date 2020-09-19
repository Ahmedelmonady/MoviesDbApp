package com.example.moviesdbapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRepository {
    val BASE_URL = "https://api.themoviedb.org/3/"
    val apiServices: ApiServices
    init {
        val retrofit = Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
        apiServices = retrofit.create(ApiServices::class.java)
    }
}