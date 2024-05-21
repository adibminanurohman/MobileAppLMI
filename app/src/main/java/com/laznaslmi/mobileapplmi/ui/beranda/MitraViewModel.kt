package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.MitraDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceMitra
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceProgram
import kotlinx.coroutines.launch

class MitraViewModel : ViewModel() {

    private val _mitraList = MutableLiveData<List<MitraDataClass>>()
    val mitraList: LiveData<List<MitraDataClass>> = _mitraList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstanceMitra.apiService.getMitraList()
                _mitraList.postValue(response.posts)
            } catch (e: Exception) {
                Log.e("MitraViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }
}