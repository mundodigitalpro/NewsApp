package com.josejordan.newsapp.repository

import com.josejordan.newsapp.data.NewsResponse


interface NewsRepository {
    suspend fun getTopHeadlines(country: String, apiKey: String): NewsResponse
}

