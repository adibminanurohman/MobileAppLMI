package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface MitraApiService {
    @GET("apilmi/mylmi-app/public/api/posts/")
    suspend fun getMitraList(@Query("category_id") categoryId: Int): MitraApiResponse
}