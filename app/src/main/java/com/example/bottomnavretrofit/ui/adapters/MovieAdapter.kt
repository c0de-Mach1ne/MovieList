package com.example.bottomnavretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bottomnavretrofit.MoveToMovieDetails
import com.example.bottomnavretrofit.R
import com.example.bottomnavretrofit.databinding.MovieItemBinding
import com.example.bottomnavretrofit.model.movie.Movie

class MovieAdapter(
    private val movieList: List<Movie>,
    private val listener: MoveToMovieDetails
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieList[position])

        holder.itemView.setOnClickListener {
            listener.moveToMovieDescription(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = MovieItemBinding.bind(itemView)

        fun onBind(movie: Movie) {
            binding.tvFullName.text = movie.name
            binding.tvTeam.text = movie.team
            binding.tvCreatedBy.text = movie.createdBy
            Glide.with(itemView.context)
                .load(movie.imageUrl)
                .into(binding.ivPreview)
        }
    }
}