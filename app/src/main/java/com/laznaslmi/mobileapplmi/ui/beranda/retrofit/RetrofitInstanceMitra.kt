package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceMitra {
    private const val BASE_URL = "http://103.179.216.69/"
    val apiService: MitraApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MitraApiService::class.java)
    }
}