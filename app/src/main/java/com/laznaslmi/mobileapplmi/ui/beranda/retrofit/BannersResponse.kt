package com.laznaslmi.mobileapplmi.ui.beranda.retrofit

data class BannerResponse(
	val success: Boolean,
	val message: String,
	val banners: List<Banner>
)

data class Banner(
	val id: Int,
	val image: String,
	val link: String,
	val user_id: Int,
	val created_at: String,
	val updated_at: String,
	val language: String,
	val type: String,
	val title: String,
	val image_full: String
)
