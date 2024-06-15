package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.PostDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceBanners
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceBerita
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class BeritaViewModel : ViewModel() {

    private val _beritaList = MutableLiveData<List<BeritaDataClass>>()
    val beritaList: LiveData<List<BeritaDataClass>> = _beritaList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchData()
    }

    internal fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstanceBerita.apiService.getBeritaList()
                _beritaList.postValue(response.posts.sortedByDescending { it.date })
            } catch (e: Exception) {
                Log.e("BeritaViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }

    private fun String.toDate(): java.util.Date? {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(this)
    }
}