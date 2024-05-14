package com.laznaslmi.mobileapplmi.ui.majalah

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.majalah.retrofit.RetrofitInstance
import kotlinx.coroutines.launch

class MajalahViewModel : ViewModel() {

    private val _majalahList = MutableLiveData<List<MajalahDataClass>>()
    val majalahList: LiveData<List<MajalahDataClass>> = _majalahList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiService.getMajalahList()
                _majalahList.postValue(response)
            }
            catch (e: Exception){
                Log.e("NotificationsViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data ${e.message}")
            }
        }
    }
}