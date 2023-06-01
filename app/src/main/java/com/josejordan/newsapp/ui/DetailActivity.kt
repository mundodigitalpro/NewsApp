package com.josejordan.newsapp.ui
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.josejordan.newsapp.data.News
import com.josejordan.newsapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater) // Initialize the binding
        setContentView(binding.root)

        val news = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable("news", News::class.java)
        } else {
            intent?.getParcelableExtra("news")
        } ?: run {
            Toast.makeText(this, "No se pudo cargar la noticia", Toast.LENGTH_SHORT).show()
            return
        }

        binding.tvTitle.text = news.title
        binding.tvAuthor.text = news.author
        binding.tvContent.text = news.content
        binding.tvPublishedAt.text = news.publishedAt

        news.urlToImage?.let {
            Glide.with(this).load(it).into(binding.ivNewsImage)
        }

    }
}
