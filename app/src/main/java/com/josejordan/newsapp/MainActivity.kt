package com.josejordan.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    private fun initializeAdapter() {
        newsAdapter = NewsAdapter()
    }

    private fun setupRecyclerView(): RecyclerView {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        return recyclerView
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
