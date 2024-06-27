package com.laznaslmi.mobileapplmi.ui.beranda.retrofit


import retrofit2.http.GET
import retrofit2.http.Query

interface BeritaApiService {
    @GET("lmizakat/public/api/posts")
    suspend fun getBeritaList(@Query("category_id") categoryId: Int): BeritaApiResponse

}