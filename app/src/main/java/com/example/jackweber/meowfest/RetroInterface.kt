package com.example.jackweber.meowfest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroInterface {

    @GET("cats")
    fun getCats(@Query("page") page: String) : Call<List<RetroCat>>

}