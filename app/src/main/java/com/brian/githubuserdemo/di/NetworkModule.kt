package com.brian.githubuserdemo.di

import android.util.Log
import com.brian.githubuserdemo.api.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule private constructor() {
    private val retrofit: Retrofit
    private val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.i("[intercept][msg]",message)
        }
    })

    private var okHttpClient : OkHttpClient
    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient().newBuilder().addInterceptor(logging).build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private val networkModule = NetworkModule()
        val client: Retrofit
            get() = networkModule.retrofit
    }
}