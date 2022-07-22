package com.example.bottomnavretrofit.data.movie

import com.example.bottomnavretrofit.model.movie.Movie
import com.example.bottomnavretrofit.model.movie.MovieListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApi {

    @GET("demos/marvel/")
    fun getMovieList(): Single<List<Movie>>
}