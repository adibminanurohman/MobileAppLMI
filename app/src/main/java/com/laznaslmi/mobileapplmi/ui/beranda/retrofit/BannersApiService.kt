package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("banners")
    fun getBanners(): Call<BannerResponse>
}
