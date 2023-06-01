package com.josejordan.newsapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.josejordan.newsapp.data.News
import com.josejordan.newsapp.databinding.NewsItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList: List<News> = ArrayList()

    fun submitList(news: List<News>) {
        this.newsList = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount() = newsList.size

    inner class NewsViewHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val news: News = newsList[position]
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra("news", news)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }

        fun bind(news: News) {
            binding.title.text = news.title
            binding.description.text = news.description

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .into(binding.image)
        }
    }
}
