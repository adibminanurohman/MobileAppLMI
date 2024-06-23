package com.laznaslmi.mobileapplmi.ui.explore

import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laznaslmi.mobileapplmi.ui.explore.retrofit.RetrofitInstanceSearchExplore
import com.laznaslmi.mobileapplmi.ui.explore.retrofit.SearchExploreDataClass
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class SearchExploreViewModel : ViewModel() {

    private val _searchExploreList = MutableLiveData<List<SearchExploreDataClass>>()
    val searchExploreList: LiveData<List<SearchExploreDataClass>> = _searchExploreList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private var originalList: List<SearchExploreDataClass> = listOf()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstanceSearchExplore.apiService.getSearchExploreList()
                originalList = response.posts
                    .map { post ->
                        post.copy(body = post.body?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString() })
                    }
                    .sortedByDescending { it.date?.toDate()?.time }
                _searchExploreList.postValue(originalList)
            } catch (e: Exception) {
                Log.e("SearchExploreViewModel", "Error fetching data", e)
                _errorMessage.postValue("Failed to load data: ${e.message}")
            }
        }
    }

    fun searchExplore(query: String?) {
        if (!query.isNullOrBlank()) {
            val filteredList = originalList.filter { explore ->
                explore.title?.contains(query, ignoreCase = true) ?: false
            }
            _searchExploreList.value = filteredList
        } else {
            _searchExploreList.value = originalList
        }
    }

    private fun String.toDate(): java.util.Date? {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.parse(this)
    }
}
