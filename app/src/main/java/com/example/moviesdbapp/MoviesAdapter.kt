package com.example.moviesdbapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_movies.view.*

class MoviesAdapter(var moviesList: MutableList<Movie>, var movieListener: MovieListener ):
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){

    interface MovieListener {
        fun movieClicked(title: String)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener(View.OnClickListener {
                var movie = itemView.getTag() as Movie
                movieListener.movieClicked(movie.title)
            })
        }

        fun onBind(movie: Movie){
            itemView.setTag(movie)
            itemView.movieTitle.setText(movie.title)
            itemView.movieReleaseDate.setText(movie.releaseDate)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder
            = MovieViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_movies, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(moviesList.get(position))
    }

    override fun getItemCount(): Int = moviesList.size

}