package com.josejordan.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josejordan.newsapp.data.News
import com.josejordan.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _newsFlow = MutableStateFlow<List<News>>(emptyList())
    val newsFlow: StateFlow<List<News>> = _newsFlow

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> = _errorFlow

fun getTopHeadlines(country: String, apiKey: String) {
    viewModelScope.launch {
        try {
            val response = repository.getTopHeadlines(country, apiKey)
            if (response.status == "ok") {
                _newsFlow.value = response.articles
            } else {
                _errorFlow.value = "There was an error fetching the news"
            }
        } catch (e: Exception) {
            _errorFlow.value = e.message
        }
    }
}
}
