//package com.example.vimarcha.modeldata
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Query
//
//class MovieApi {
//    companion object {
//        private const val BASE_URL = "https://imdb-api.com"
//        private const val API_KEY = "k_jbn4lx36"
//
//
//        private val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        fun create(): MovieService {
//            return retrofit.create(MovieService::class.java)
//        }
//    }
//}
//interface MovieService {
//    @GET("/en/API/SearchTitle/$API_KEY")
//    suspend fun searchMovies(@Query("expression") query: String): ApiResponse<SearchResult>
//}