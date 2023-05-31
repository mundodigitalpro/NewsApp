package com.josejordan.newsapp

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>
)