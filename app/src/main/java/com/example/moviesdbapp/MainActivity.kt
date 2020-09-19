package com.example.moviesdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var moviesList: MutableList<Movie>
    var currentPageNumber: Int = 1
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAdapter = MoviesAdapter(mutableListOf())
        linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = moviesAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

    fun getPopularMovies(){
        MoviesRepository.fetchPopularMovies(
            page = 1,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    fun onError(){
        Toast.makeText(this, "Failed to fetch Movies", Toast.LENGTH_SHORT).show()
    }

    fun onPopularMoviesFetched(moviesList : MutableList<Movie>) {
        moviesAdapter.appendMovies(moviesList)
    }
}