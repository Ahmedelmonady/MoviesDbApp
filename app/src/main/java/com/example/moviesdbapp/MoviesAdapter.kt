package com.example.moviesdbapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import kotlinx.android.synthetic.main.card_movies.view.*

class MoviesAdapter(var moviesList: MutableList<Movie>? ):
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){

    interface MovieListener {
        fun movieClicked(movieId: Long)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener(View.OnClickListener {
                var movie = itemView.getTag() as Movie
            })
        }

        fun onBind(movie: Movie){
            itemView.setTag(movie)
            itemView.movieTitle.text = movie.title
            itemView.movieReleaseDate.text = movie.releaseDate
            itemView.movieRating.text = movie.rating.toString()

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(itemView.moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder
            = MovieViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.card_movies, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(moviesList!!.get(position))
    }

    override fun getItemCount(): Int = moviesList!!.size

    fun appendMovies(movies: MutableList<Movie>){
        this.moviesList!!.addAll(movies)
        notifyItemRangeChanged(
            this.moviesList!!.size,
            moviesList!!.size-1
        )
    }

}