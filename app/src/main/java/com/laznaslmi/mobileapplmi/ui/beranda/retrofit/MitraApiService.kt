package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.http.GET

interface MitraApiService {
    @GET("apilmi/mylmi-app/public/api/posts/")
    suspend fun getMitraList(): MitraApiResponse
}