package com.laznaslmi.mobileapplmi.ui.majalah.retrofit

import com.laznaslmi.mobileapplmi.ui.majalah.MajalahDataClass
import retrofit2.http.GET

interface MajalahApiService {
    @GET("http://103.179.216.69/apilmi/mylmi-app/public/api/magazines/")
    suspend fun getMajalahList(): List<MajalahDataClass>
}