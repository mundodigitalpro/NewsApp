package com.josejordan.newsapp.data

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)