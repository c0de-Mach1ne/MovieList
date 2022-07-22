package com.example.bottomnavretrofit

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bottomnavretrofit.data.movie.MovieApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    lateinit var movieApi: MovieApi

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit() {
        val httpLoginInterceptor = HttpLoggingInterceptor()
        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .addInterceptor(ChuckerInterceptor.Builder(applicationContext).build())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.simplifiedcoding.net/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        movieApi = retrofit.create(MovieApi::class.java)
    }
}