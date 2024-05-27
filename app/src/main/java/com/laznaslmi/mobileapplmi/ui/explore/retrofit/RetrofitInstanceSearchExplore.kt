package com.laznaslmi.mobileapplmi.ui.explore.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceSearchExplore {
    private const val BASE_URL = "http://103.179.216.69/"
    val apiService: SearchExploreApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchExploreApiService::class.java)
    }
}