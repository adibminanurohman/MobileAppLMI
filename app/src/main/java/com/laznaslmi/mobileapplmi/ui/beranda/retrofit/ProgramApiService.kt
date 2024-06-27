package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface ProgramApiService {
    @GET("lmizakat/public/api/posts")
    suspend fun getProgramList(@Query("category_id") categoryId: Int): ProgramApiResponse
}