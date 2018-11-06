package com.example.jackweber.meowfest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    private val BASE_URL = "https://chex-triplebyte.herokuapp.com/api/"
    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getInstance(): Retrofit {
        return retrofit
    }

}