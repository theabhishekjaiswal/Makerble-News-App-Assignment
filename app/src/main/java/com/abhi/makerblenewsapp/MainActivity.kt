package com.abhi.makerblenewsapp

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnBookmarks: Button = findViewById(R.id.btn_bookmarks)
        val btnNews: Button = findViewById(R.id.btn_news)
        val fragmentContainer: FrameLayout = findViewById(R.id.fragment_container)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BookmarksFragment())
                .commit()
        }

        btnBookmarks.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, BookmarksFragment())
                .addToBackStack(null)
                .commit()

            btnBookmarks.setBackgroundColor(resources.getColor(R.color.orange))
            btnNews.setBackgroundColor(resources.getColor(R.color.c2))
        }

        btnNews.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NewsFragment())
                .addToBackStack(null)
                .commit()
            btnNews.setBackgroundColor(resources.getColor(R.color.orange))
            btnBookmarks.setBackgroundColor(resources.getColor(R.color.c2))
        }
    }
}