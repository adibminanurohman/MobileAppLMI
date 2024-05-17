package com.laznaslmi.mobileapplmi.ui.majalah

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MajalahDataClass(
    val image: String,
    val title: String,
    val release: String,
    val description: String,
    val views: Int?,
    val link: String?
): Parcelable
