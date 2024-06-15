package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.PostDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceBanners
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class BannersViewModel : ViewModel() {

    private val _bannersList = MutableLiveData<List<PostDataClass>>()
    val bannersList: LiveData<List<PostDataClass>> = _bannersList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchData()
    }

    internal fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstanceBanners.apiService.getBannersList()
                _bannersList.postValue(response.posts.sortedByDescending { it.date?.toDate()?.time })
            } catch (e: Exception) {
                Log.e("BannersViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }

    private fun String.toDate(): java.util.Date? {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(this)
    }
}
