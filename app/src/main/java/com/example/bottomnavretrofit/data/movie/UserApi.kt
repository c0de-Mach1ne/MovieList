package com.example.bottomnavretrofit.data.movie

import com.example.bottomnavretrofit.model.users.User
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun getUsers(): Single<List<User>>
}