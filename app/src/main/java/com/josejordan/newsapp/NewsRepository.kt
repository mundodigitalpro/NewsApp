package com.josejordan.newsapp

interface NewsRepository {
    suspend fun getTopHeadlines(country: String, apiKey: String): NewsResponse
}
