package com.josejordan.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josejordan.newsapp.viewmodel.NewsViewModel
import com.josejordan.newsapp.databinding.ActivityMainBinding
import com.josejordan.newsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        bindAdapterToRecyclerView(binding.recyclerView)

        observeNewsLiveData()

        fetchTopHeadlines(Constants.COUNTRY, Constants.API_KEY)
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

