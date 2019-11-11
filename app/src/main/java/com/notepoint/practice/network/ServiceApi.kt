package com.notepoint.practice.network

import retrofit2.Call
import retrofit2.http.GET

interface ServiceApi {

    @GET("marvel/")
    fun getHeroes():Call<List<Heroes>>
}