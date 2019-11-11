package com.notepoint.practice.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL ="https://simplifiedcoding.net/demos/"

//val gson = GsonBuilder().create()

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()


object RetrofitInstance {
    val retrofitInstance: ServiceApi by lazy{
        retrofit.create(ServiceApi::class.java)
    }
}