package com.josejordan.newsapp
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import com.josejordan.newsapp.api.NewsAPI
import com.josejordan.newsapp.data.NewsResponse
import com.josejordan.newsapp.repository.NewsRepository
import com.josejordan.newsapp.repository.NewsRepositoryImpl
import org.junit.Assert.assertEquals

class NewsRepositoryTest {

    // Instantiate mock objects
    private lateinit var newsApi: NewsAPI
    private lateinit var repository: NewsRepository

    @Before
    fun setup() {
        // Initialize mock objects
        newsApi = mock(NewsAPI::class.java)
        repository = NewsRepositoryImpl(newsApi)
    }

    @Test
    fun getTopHeadlines_callsApi() = runBlocking {
        // Arrange
        val country = "us"
        val apiKey = "123456"
        val response = NewsResponse("ok", 10, emptyList())
        `when`(newsApi.getTopHeadlines(country, apiKey)).thenReturn(response)

        // Act
        val result = repository.getTopHeadlines(country, apiKey)

        // Assert
        verify(newsApi).getTopHeadlines(country, apiKey)
        assertEquals(response, result)
    }
}
