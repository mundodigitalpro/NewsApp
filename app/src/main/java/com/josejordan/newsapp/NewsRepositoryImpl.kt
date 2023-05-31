package com.josejordan.newsapp

class NewsRepositoryImpl(private val newsApi: NewsAPI) : NewsRepository {
    override suspend fun getTopHeadlines(country: String, apiKey: String): NewsResponse {
        return newsApi.getTopHeadlines(country, apiKey)
    }
}
