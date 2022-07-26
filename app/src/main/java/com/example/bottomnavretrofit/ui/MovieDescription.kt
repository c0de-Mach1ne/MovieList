package com.example.bottomnavretrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bottomnavretrofit.App.Companion.ARG_MOVIE
import com.example.bottomnavretrofit.R
import com.example.bottomnavretrofit.databinding.MovieDescriptionBinding
import com.example.bottomnavretrofit.model.movie.Movie

class MovieDescription : Fragment() {

    private lateinit var binding: MovieDescriptionBinding
    private val movie
        get() = requireArguments().getSerializable(ARG_MOVIE) as Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MovieDescriptionBinding.inflate(layoutInflater, container, false)
        binding.tvCreatedBy.text = movie.createdBy
        binding.tvTeam.text = movie.team
        binding.tvFullName.text = movie.realName
        Glide.with(binding.ivPreview.context)
            .load(movie.imageUrl)
            .into(binding.ivPreview)

        return binding.root
    }
}