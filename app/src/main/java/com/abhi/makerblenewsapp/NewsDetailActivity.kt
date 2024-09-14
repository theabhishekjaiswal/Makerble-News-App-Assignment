package com.abhi.makerblenewsapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        article = intent.getParcelableExtra("ARTICLE") ?: return

        val ivDetailImage: ImageView = findViewById(R.id.iv_detail_image)

        findViewById<TextView>(R.id.tv_detail_title).text = article.title
        findViewById<TextView>(R.id.tv_detail_description).text = article.description
        findViewById<TextView>(R.id.tv_detail_content).text = article.content
        findViewById<TextView>(R.id.tv_detail_author).text = article.author
        findViewById<TextView>(R.id.tv_detail_published_at).text = article.publishedAt

        Glide.with(this)
            .load(article.urlToImage)
            .apply(RequestOptions().placeholder(R.drawable.img_1).error(R.drawable.baseline_swap_vertical_circle_24))
            .into(ivDetailImage)

        val bookmarks = BookmarkManager.getBookmarks(this)
        val isBookmarked = bookmarks.contains(article)
        val bookmarkButton = findViewById<ImageView>(R.id.btnBookmark)
        bookmarkButton.setImageResource(
            if (isBookmarked) R.drawable.baseline_bookmarked else R.drawable.baseline_bookmark_border_24
        )

        bookmarkButton.setOnClickListener {
            if (isBookmarked) {
                BookmarkManager.removeBookmark(this, article)
                bookmarkButton.setImageResource(R.drawable.baseline_bookmark_border_24)
            } else {
                BookmarkManager.addBookmark(this, article)
                bookmarkButton.setImageResource(R.drawable.baseline_bookmarked)
            }
        }
    }
}
