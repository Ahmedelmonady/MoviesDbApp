package com.example.moviesdbapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "1e562584a664e6142e07a710b0495bbb"
    ): Call<MoviesResponse>

}