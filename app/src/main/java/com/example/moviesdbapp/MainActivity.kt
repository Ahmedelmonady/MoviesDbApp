package com.example.moviesdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MoviesAdapter.MovieListener {

    lateinit var moviesList: MutableList<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MoviesAdapter(moviesList, this)

    }

    override fun movieClicked(movieId: Long) {
        TODO("Not yet implemented")
    }
}