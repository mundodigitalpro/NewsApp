package com.josejordan.newsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {

    val newsLiveData = MutableLiveData<List<News>>()
    private val errorLiveData = MutableLiveData<String>()

    fun getTopHeadlines(country: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(country, apiKey)
                if (response.status == "ok") {
                    newsLiveData.value = response.articles
                } else {
                    errorLiveData.value = "There was an error fetching the news"
                }
            } catch (e: Exception) {
                errorLiveData.value = e.message
            }
        }
    }
}
