package com.example.moviesdbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MoviesAdapter.MovieListener {

    lateinit var moviesList: MutableList<Movie>
    var currentPageNumber: Int = 1
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesAdapter = MoviesAdapter(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        recyclerView.adapter = moviesAdapter
        recyclerView.layoutManager = linearLayoutManager


        getPopularMovies()

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
        moviesAdapter.appendMovies(moviesList)
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

    override fun movieClicked(movie: Movie) {
        var intent = Intent(this, MovieDetailsActivity::class.java)

        intent.putExtra("movie", Gson().toJson(movie))

        startActivity(intent)
        finish()
    }
}