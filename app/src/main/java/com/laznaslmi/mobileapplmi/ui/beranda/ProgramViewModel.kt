package com.laznaslmi.mobileapplmi.ui.beranda

import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.ProgramDataClass
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.RetrofitInstanceProgram
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class ProgramViewModel : ViewModel() {

    private val _programList = MutableLiveData<List<ProgramDataClass>>()
    val programList: LiveData<List<ProgramDataClass>> = _programList

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
                val response = RetrofitInstanceProgram.apiService.getProgramList(categoryId = 2)
                if (response.success) {
                    val sortedPosts = response.posts
                        .filter { it.categoryId == 2 }
                        .map { post ->
                            post.copy(
                                body = post.body?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() },
                                image = fixImageUrl(post.image)
                            )
                        }
                        .sortedByDescending { it.date?.toDate()?.time }
                    _programList.postValue(sortedPosts)
                } else {
                    _errorMessage.postValue("Failed to load data: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("ProgramViewModel", "Error fetching data", e)
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
