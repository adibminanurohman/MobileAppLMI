package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.http.GET

interface EdukasiApiService {
    @GET("apilmi/mylmi-app/public/api/posts/")
    suspend fun getEdukasiList(): EdukasiApiResponse
}