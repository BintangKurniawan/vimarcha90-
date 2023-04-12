package com.example.vimarcha

import com.example.vimarcha.modeldata.MovieDetailData
import com.example.vimarcha.modeldata.PopularMovie
import com.example.vimarcha.modeldata.SearchData
import com.example.vimarcha.modeldata.TrendMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    // YANG SEDANG TAYANG CARITA NA MAH
    @GET("en/API/InTheaters/")
    suspend fun getTrendMovies(
        @Query("apikey") apiKey: String
    ): TrendMovie

    // PALING POPULER BERDASARKAN RATING, TAPI AING TE NYAHO NANAON
    @GET("en/API/MostPopularMovies/")
    suspend fun getPopularMovies(
        @Query("apikey") apiKey: String
    ): PopularMovie

    // BUAT KE DETAIL
    @GET("en/API/Title/")
    fun getDetailMovie(
        @Query("apiKey") apiKey:String,
        @Query("id") id:String?
    ): Call<MovieDetailData>

    // FITUR SAVE
    @GET("en/API/SearchTitle/")
    fun getSearch(
        @Query("apiKey") apiKey:String,
        @Query("expression") title: String?
    ): Call<SearchData>
}