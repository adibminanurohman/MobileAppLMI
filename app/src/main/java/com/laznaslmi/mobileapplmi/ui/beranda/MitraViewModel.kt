package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.MitraDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceMitra
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MitraViewModel : ViewModel() {

    private val _mitraList = MutableLiveData<List<MitraDataClass>>()
    val mitraList: LiveData<List<MitraDataClass>> = _mitraList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        fetchData()
    }

    fun refreshData() {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstanceMitra.apiService.getMitraList(categoryId = 5)
                if (response.success) {
                    _mitraList.postValue(response.posts
                        .filter { it.categoryId == 5 }
                        .map { post ->
                            post.copy(body = post.body?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() })
                        }
                        .sortedByDescending { it.date?.toDate()?.time }
                    )
                } else {
                    _errorMessage.postValue("Failed to load data: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("MitraViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }

    private fun String.toDate(): java.util.Date? {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(this)
    }
}
