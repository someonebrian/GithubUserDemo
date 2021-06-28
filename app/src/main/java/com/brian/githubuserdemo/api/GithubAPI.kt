package com.brian.githubuserdemo.api

import com.brian.githubuserdemo.model.UserData
import retrofit2.Call
import retrofit2.http.GET


const val BASE_URL = "https://api.github.com/"

interface GithubAPI {
    @GET("users")
    fun getUsers(): Call<List<UserData>>
}