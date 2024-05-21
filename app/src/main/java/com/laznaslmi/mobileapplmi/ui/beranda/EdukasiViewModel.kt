package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.EdukasiDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceEdukasi
import kotlinx.coroutines.launch

class EdukasiViewModel : ViewModel() {

    private val _edukasiList = MutableLiveData<List<EdukasiDataClass>>()
    val edukasiList: LiveData<List<EdukasiDataClass>> = _edukasiList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstanceEdukasi.apiService.getEdukasiList()
                _edukasiList.postValue(response.posts)
            } catch (e: Exception) {
                Log.e("EdukasiViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }
}