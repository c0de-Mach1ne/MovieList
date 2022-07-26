package com.example.bottomnavretrofit

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.bottomnavretrofit.data.api.MovieApi
import com.example.bottomnavretrofit.data.api.UserApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var movieApi: MovieApi
    lateinit var userApi: UserApi

    override fun onCreate() {
        super.onCreate()
        configureRetrofit()
    }

    private fun configureRetrofit() {

        val httpLoginInterceptor = HttpLoggingInterceptor()
        httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            // нужен, чтобы при запуске приложения открывалась утилита с отображением запросов
            .addInterceptor(ChuckerInterceptor.Builder(applicationContext).build())
            .build()

        val movieRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_MOVIE)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        movieApi = movieRetrofit.create(MovieApi::class.java)

        val userRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_USER)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        userApi = userRetrofit.create(UserApi::class.java)
    }

    companion object {

        const val ARG_MOVIE = "ARG_MOVIE"
        const val BASE_URL_MOVIE = "https://www.simplifiedcoding.net/"
        const val BASE_URL_USER = "https://jsonplaceholder.typicode.com/"
    }
}
