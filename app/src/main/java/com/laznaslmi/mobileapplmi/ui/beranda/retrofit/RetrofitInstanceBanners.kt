package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceBanners {
    private const val BASE_URL = "http://103.179.216.69/"
    val apiService: BannersApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BannersApiService::class.java)
    }
}