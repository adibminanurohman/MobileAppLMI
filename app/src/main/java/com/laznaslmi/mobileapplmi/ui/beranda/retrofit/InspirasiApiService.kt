package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface InspirasiApiService {
    @GET("apilmi/mylmi-app/public/api/posts/")
    suspend fun getInspirasiList(@Query("category_id") categoryId: Int): InspirasiApiResponse
}