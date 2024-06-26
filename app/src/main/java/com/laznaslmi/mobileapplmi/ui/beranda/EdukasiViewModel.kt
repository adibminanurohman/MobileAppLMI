package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.EdukasiDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceEdukasi
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class EdukasiViewModel : ViewModel() {

    private val _edukasiList = MutableLiveData<List<EdukasiDataClass>>()
    val edukasiList: LiveData<List<EdukasiDataClass>> = _edukasiList

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
                val response = RetrofitInstanceEdukasi.apiService.getEdukasiList(categoryId = 4)
                if (response.success) {
                    _edukasiList.postValue(response.posts
                        .filter { it.categoryId == 4 }
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
                Log.e("EdukasiViewModel", "Error fetching data", e)
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
