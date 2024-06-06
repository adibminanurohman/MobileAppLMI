package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface EdukasiApiService {
    @GET("apilmi/mylmi-app/public/api/posts/")
    suspend fun getEdukasiList(@Query("category_id") categoryId: Int): EdukasiApiResponse

}