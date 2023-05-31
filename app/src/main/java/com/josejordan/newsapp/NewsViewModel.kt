package com.josejordan.newsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val newsApi = NewsAPI.create()
    val newsLiveData = MutableLiveData<List<News>>()

    fun getTopHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            val response = newsApi.getTopHeadlines(country, apiKey)
            if (response.status == "ok") {
                newsLiveData.value = response.articles
            } else {
                // Handle the error here
            }
        }
    }
}
