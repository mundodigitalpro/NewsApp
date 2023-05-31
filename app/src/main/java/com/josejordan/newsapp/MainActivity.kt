package com.josejordan.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josejordan.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize the binding
        setContentView(binding.root)

        initializeViewModel()
        initializeAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        bindAdapterToRecyclerView(binding.recyclerView)

        observeNewsLiveData()

        fetchTopHeadlines(Constants.COUNTRY, Constants.API_KEY)
    }

    private fun initializeViewModel() {
        val newsApi = NewsAPI.create()
        val newsRepository = NewsRepositoryImpl(newsApi)

        newsViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return NewsViewModel(newsRepository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        })[NewsViewModel::class.java]
    }



    private fun initializeAdapter() {
        newsAdapter = NewsAdapter()
    }

    private fun bindAdapterToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = newsAdapter
    }

    private fun observeNewsLiveData() {
        newsViewModel.newsLiveData.observe(this) { news ->
            newsAdapter.submitList(news)
        }
    }

    private fun fetchTopHeadlines(country: String, apiKey: String) {
        newsViewModel.getTopHeadlines(country, apiKey)
    }
}
