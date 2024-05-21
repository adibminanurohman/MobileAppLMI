package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.InspirasiDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceInspirasi
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceProgram
import kotlinx.coroutines.launch

class InspirasiViewModel : ViewModel() {

    private val _inspirasiList = MutableLiveData<List<InspirasiDataClass>>()
    val inspirasiList: LiveData<List<InspirasiDataClass>> = _inspirasiList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstanceInspirasi.apiService.getInspirasiList()
                _inspirasiList.postValue(response.posts)
            } catch (e: Exception) {
                Log.e("InspirasiViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }
}