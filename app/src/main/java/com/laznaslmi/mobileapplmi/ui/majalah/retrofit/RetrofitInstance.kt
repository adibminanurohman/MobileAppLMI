package com.laznaslmi.mobileapplmi.ui.majalah.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://103.179.216.69/apilmi/mylmi-app/public/api/magazines/"
    val apiService: MajalahApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MajalahApiService::class.java)
    }
}