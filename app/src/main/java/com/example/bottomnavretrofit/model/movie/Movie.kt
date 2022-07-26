package com.example.bottomnavretrofit.model.movie

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    val bio: String,
    @SerializedName("createdby")
    val createdBy: String,
    @SerializedName("firstappearance")
    val firstAppearance: String,
    @SerializedName("imageurl")
    val imageUrl: String,
    val name: String,
    val publisher: String,
    @SerializedName("realname")
    val realName: String,
    val team: String
): Serializable