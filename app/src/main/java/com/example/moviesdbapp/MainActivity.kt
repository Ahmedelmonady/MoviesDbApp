package com.example.moviesdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
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

        attachOnScrollListener()
    }

    fun getPopularMovies(){
        MoviesRepository.fetchPopularMovies(
            currentPageNumber,
            ::onPopularMoviesFetched,
            ::onError
        )
    }

    fun onError(){
        Toast.makeText(this, "Failed to fetch Movies", Toast.LENGTH_SHORT).show()
    }

    fun onPopularMoviesFetched(moviesList : MutableList<Movie>) {
        attachOnScrollListener()
    }

    fun attachOnScrollListener(){
        recyclerView.addOnScrollListener(object: OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = linearLayoutManager.itemCount
                val visibleItemsCount = linearLayoutManager.childCount
                val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

                if(firstVisibleItem + visibleItemsCount >= totalItems / 2){
                    recyclerView.removeOnScrollListener(this)
                    currentPageNumber++
                    getPopularMovies()
                }
            }
        })
    }
}