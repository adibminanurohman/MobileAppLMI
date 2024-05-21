package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceProgram {
    private const val BASE_URL = "http://103.179.216.69/"
    val apiService: ProgramApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProgramApiService::class.java)
    }
}