package com.josejordan.newsapp
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
//import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val news = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.extras?.getParcelable("news", News::class.java)
        } else {
            intent?.getParcelableExtra("news")
        } ?: run {
            Toast.makeText(this, "No se pudo cargar la noticia", Toast.LENGTH_SHORT).show()
            return
        }


        val titleTextView = findViewById<TextView>(R.id.tvTitle)
        titleTextView.text = news.title

        val authorTextView = findViewById<TextView>(R.id.tvAuthor)
        authorTextView.text = news.author

        val descriptionTextView = findViewById<TextView>(R.id.tvDescription)
        descriptionTextView.text = news.description

        val contentTextView = findViewById<TextView>(R.id.tvContent)
        contentTextView.text = news.content

        val publishedAtTextView = findViewById<TextView>(R.id.tvPublishedAt)
        publishedAtTextView.text = news.publishedAt

/*        val newsImageView = findViewById<ImageView>(R.id.ivNewsImage)
        news.urlToImage?.let {
            Picasso.get().load(it).into(newsImageView)
        }*/

        val newsImageView = findViewById<ImageView>(R.id.ivNewsImage)
        news.urlToImage?.let {
            Glide.with(this).load(it).into(newsImageView)
        }

    }
}
