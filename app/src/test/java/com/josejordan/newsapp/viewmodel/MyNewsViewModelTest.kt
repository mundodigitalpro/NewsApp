package com.josejordan.newsapp.viewmodel

import com.josejordan.newsapp.api.NewsAPI
import com.josejordan.newsapp.data.NewsResponse
import com.josejordan.newsapp.repository.NewsRepository
import com.josejordan.newsapp.repository.NewsRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MyNewsViewModelTest{

    // Instantiate mock objects
    private lateinit var newsApi: NewsAPI
    private lateinit var repository: NewsRepository
    private lateinit var viewModel: NewsViewModel

    @Before
    fun setup() {
        // Initialize mock objects
        newsApi = mock(NewsAPI::class.java)
        repository = NewsRepositoryImpl(newsApi)
        viewModel = NewsViewModel(repository)
    }

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun checkTestsWork() = coroutinesTestRule.testDispatcher.runBlockingTest{
        Assert.assertTrue(true)
    }

    @Test
    fun `Listening News`()= coroutinesTestRule.testDispatcher.runBlockingTest{
        val response = NewsResponse(fakeStatus, fakeTotalResult, fakeNews)
        `when`(newsApi.getTopHeadlines(fakeCountry, fakeApiKey)).thenReturn(response)
        val result = repository.getTopHeadlines(fakeCountry, fakeApiKey)
        verify(newsApi).getTopHeadlines(fakeCountry, fakeApiKey)
        assertEquals(response, result)
    }

}