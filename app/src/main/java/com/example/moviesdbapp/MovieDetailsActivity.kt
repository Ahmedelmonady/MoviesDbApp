package com.example.moviesdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        var movie = Gson().fromJson(intent.extras!!.get("movie").toString(), Movie::class.java)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
            .transform(CenterCrop())
            .into(moviePoster)

        movieTitle.text = movie.title
        movieReleaseDate.text = movie.releaseDate
        movieRating.text = "Rating ${movie.rating} / 10.0"
        movieOverview.text = movie.overview

    }
}