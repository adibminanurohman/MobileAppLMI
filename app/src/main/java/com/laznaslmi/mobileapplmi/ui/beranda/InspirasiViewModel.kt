package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.InspirasiDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceInspirasi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class InspirasiViewModel : ViewModel() {

    private val _inspirasiList = MutableLiveData<List<InspirasiDataClass>>()
    val inspirasiList: LiveData<List<InspirasiDataClass>> = _inspirasiList

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
                val response = RetrofitInstanceInspirasi.apiService.getInspirasiList(categoryId = 3)
                if (response.success) {
                    _inspirasiList.postValue(response.posts
                        .filter { it.categoryId == 3 }
                        .map { post ->
                            post.copy(
                                body = post.body?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() },
                                image = fixImageUrl(post.image)
                            )
                        }
                        .sortedByDescending { it.date?.toDate()?.time }
                    )
                } else {
                    _errorMessage.postValue("Failed to load data: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("InspirasiViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }

    private fun fixImageUrl(imageUrl: String?): String {
        return imageUrl?.let { "http://msib6.lmizakat.id/lmizakat/public/storage/$it" } ?: ""
    }

    private fun String.toDate(): java.util.Date? {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(this)
    }
}
