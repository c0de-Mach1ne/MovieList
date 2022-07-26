package com.example.bottomnavretrofit.data.api

import com.example.bottomnavretrofit.model.movie.Movie
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApi {
    @GET("demos/marvel/")
    fun getMovieList(): Single<List<Movie>>
}