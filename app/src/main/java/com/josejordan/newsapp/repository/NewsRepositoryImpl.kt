package com.josejordan.newsapp.repository

import com.josejordan.newsapp.api.NewsAPI
import com.josejordan.newsapp.data.NewsResponse
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsAPI) : NewsRepository {

    override suspend fun getTopHeadlines(country: String, apiKey: String): NewsResponse {
        return newsApi.getTopHeadlines(country, apiKey)
    }
}

