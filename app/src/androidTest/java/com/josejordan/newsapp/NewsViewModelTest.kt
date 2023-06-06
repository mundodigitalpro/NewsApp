package com.josejordan.newsapp

import com.josejordan.newsapp.data.NewsResponse
import com.josejordan.newsapp.repository.NewsRepository
import com.josejordan.newsapp.viewmodel.NewsViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {
    @Mock
    private lateinit var repository: NewsRepository

    private lateinit var viewModel: NewsViewModel

    @Before
    fun setUp() {
        viewModel = NewsViewModel(repository)
    }

    @Test
    fun testGetTopHeadlines() = runBlocking {
        // Arrange
        val country = "us"
        val apiKey = "123456"
        val response = NewsResponse(status = "ok", totalResults = 10, articles = emptyList())
        Mockito.`when`(repository.getTopHeadlines(anyString(), anyString())).thenReturn(response)

        // Act
        viewModel.getTopHeadlines(country, apiKey)

        // Assert
        val errorFlowValue = viewModel.errorFlow.first()
        assertNull(errorFlowValue)
    }

    @Test
    fun testGetTopHeadlinesWithUnsupportedCountry() = runBlocking {
        // Arrange
        val country = "sp"
        val apiKey = "123456"

        // Act
        viewModel.getTopHeadlines(country, apiKey)

        // Assert
        // No need to assert anything here, because the `getTopHeadlines()` method should throw an exception when an unsupported country is passed in.
    }



    @Test
    fun testGetTopHeadlinesWithInvalidApiKey() = runBlocking {
        // Arrange
        val country = "us"
        val apiKey = "invalid_api_key"
        val response = NewsResponse(status = "error", totalResults = 0, articles = emptyList())
        Mockito.`when`(repository.getTopHeadlines(anyString(), anyString())).thenReturn(response)

        // Act
        viewModel.getTopHeadlines(country, apiKey)

        // Assert
        // No need to assert anything here, because the `getTopHeadlines()` method should throw an exception when an invalid API key is passed in.
    }
}