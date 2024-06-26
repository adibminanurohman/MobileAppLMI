package com.laznaslmi.mobileapplmi.ui.beranda

import android.text.Html
import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.beranda.retrofit.BeritaDataClass
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
                val response = RetrofitInstanceBerita.apiService.getBeritaList(categoryId = 1)
                val postsWithPlainText = response.posts
                    .filter { it.categoryId == 1 }
                    .map { post ->
                    post.copy(
                        body = post.body?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() },
                        image = fixImageUrl(post.image)
                    )
                }
                _beritaList.postValue(postsWithPlainText.sortedByDescending { it.date })
            } catch (e: Exception) {
                Log.e("BeritaViewModel", "Error fetching data", e)
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
