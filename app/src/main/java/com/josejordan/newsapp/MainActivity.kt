package com.josejordan.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViewModel()
        initializeAdapter()

        val recyclerView = setupRecyclerView()

        bindAdapterToRecyclerView(recyclerView)

        observeNewsLiveData()

        fetchTopHeadlines("us", "fbc62461560d4bd1a4d556eec82cda4b")
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
