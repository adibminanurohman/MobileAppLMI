package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceProgram
import kotlinx.coroutines.launch

class ProgramViewModel : ViewModel() {

    private val _programList = MutableLiveData<List<ProgramDataClass>>()
    val programList: LiveData<List<ProgramDataClass>> = _programList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstanceProgram.apiService.getProgramList(categoryId = 2)
                if (response.success) {
                    _programList.postValue(response.posts.filter { it.categoryId == 2 })
                } else {
                    _errorMessage.postValue("Failed to load data: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("ProgramViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }
}
