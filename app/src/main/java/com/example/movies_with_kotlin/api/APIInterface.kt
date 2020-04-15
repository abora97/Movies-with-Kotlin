package com.example.movies_with_kotlin.api

import PopularResponse



import com.example.movies_with_kotlin.util.Constants.API_KEY
import com.example.movies_with_kotlin.util.Constants.MOVIE_LANGUAGE
import com.example.movies_with_kotlin.util.Constants.PAGE_KEY
import com.example.movies_with_kotlin.util.Constants.POPULAR_URL
import com.example.movies_with_kotlin.util.Constants.TOP_URL
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface APIInterface {




    @GET(POPULAR_URL)
    fun getPopular(
        @Query(API_KEY) apiKey: String,
        @Query(MOVIE_LANGUAGE) language: String,
        @Query(PAGE_KEY) page: Int
    ): Call<PopularResponse>

    @GET(TOP_URL)
    fun getTopRated(
        @Query(API_KEY) apiKey: String,
        @Query(MOVIE_LANGUAGE) language: String,
        @Query(PAGE_KEY) page: Int
    ): Call<PopularResponse>


}