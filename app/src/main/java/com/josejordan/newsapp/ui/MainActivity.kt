package com.josejordan.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.josejordan.newsapp.viewmodel.NewsViewModel
import com.josejordan.newsapp.databinding.ActivityMainBinding
import com.josejordan.newsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        //observeNewsLiveData()
        observeNewsFlow()
        observeErrorFlow()


        fetchTopHeadlines(Constants.COUNTRY, Constants.API_KEY)
    }

    private fun initializeAdapter() {
        newsAdapter = NewsAdapter()
    }

    private fun bindAdapterToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = newsAdapter
    }

/*    private fun observeNewsLiveData() {
        newsViewModel.newsLiveData.observe(this) { news ->
            newsAdapter.submitList(news)
        }
    }*/
    private fun observeNewsFlow() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.newsFlow.collect { news ->
                    newsAdapter.submitList(news)
                }
            }
        }
    }

    private fun observeErrorFlow() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.errorFlow.collect { error ->
                    error?.let {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }



    private fun fetchTopHeadlines(country: String, apiKey: String) {
        newsViewModel.getTopHeadlines(country, apiKey)
    }
}

