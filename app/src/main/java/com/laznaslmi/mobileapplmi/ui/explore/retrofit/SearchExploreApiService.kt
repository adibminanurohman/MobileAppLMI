package com.laznaslmi.mobileapplmi.ui.explore.retrofit

import retrofit2.http.GET

interface SearchExploreApiService {
    @GET("lmizakat/public/api/posts")
    suspend fun getSearchExploreList(): SearchExploreApiResponse
}