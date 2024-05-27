package com.laznaslmi.mobileapplmi.ui.explore.retrofit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class SearchExploreApiResponse(
    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("posts")
    val posts: List<SearchExploreDataClass>
)

@Parcelize
data class SearchExploreDataClass(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("body")
    val body: String? = null,

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("views")
    val views: Int? = null,

    @field:SerializedName("language")
    val language: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("category_id")
    val categoryId: Int? = null,

    @field:SerializedName("user_id")
    val userId: Int? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null
): Parcelable