package com.example.vimarcha

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RClient {
    // MASUKIN API
    private const val BASE_URL = "https://imdb-api.com/"

    val instances:API by lazy {
        val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        retrofit.create(API::class.java)
    }

}