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

class MainActivity : AppCompatActivity(), MoviesAdapter.MovieListener {

    lateinit var moviesList: MutableList<Movie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPopularMovies()

        //recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //recyclerView.adapter = MoviesAdapter(moviesList, this)

    }

    override fun movieClicked(movieId: Long) {
        TODO("Not yet implemented")
    }

    fun getPopularMovies(){
        MoviesRepository.apiServices.getPopularMovies()
            .enqueue(object: Callback<MoviesResponse>{
                override fun onResponse(
                    call: Call<MoviesResponse>,
                    response: Response<MoviesResponse>
                ) {
                    if(response.isSuccessful){
                        Log.d("repo", "${response.body()?.movies}")
                    }
                    else{
                        Log.d("repo", "Response is not successful")
                    }
                }

                override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "Failed to load movies", Toast.LENGTH_LONG).show()
                }

            })
    }
}