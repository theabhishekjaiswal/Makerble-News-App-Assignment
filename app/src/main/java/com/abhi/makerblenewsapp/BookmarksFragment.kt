package com.abhi.makerblenewsapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi.makerblenewsapp.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding
    private lateinit var bookmarksAdapter: NewsAdapter
    private val bookmarkedArticles = mutableListOf<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility=View.VISIBLE
        binding.emptybg.visibility=View.VISIBLE
        setupRecyclerView()
        loadBookmarkedArticles()
    }

    private fun setupRecyclerView() {
        bookmarksAdapter = NewsAdapter(bookmarkedArticles) { article -> onArticleClicked(article) }
        binding.rcvBookmarks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarksAdapter
        }
    }

    private fun loadBookmarkedArticles() {
        val articles = BookmarkManager.getBookmarks(requireContext())
        if(articles.isEmpty()) {
            binding.emptybg.visibility=View.VISIBLE
        }else{
            binding.emptybg.visibility=View.GONE
        }
        bookmarkedArticles.clear()
        bookmarkedArticles.addAll(articles)
        bookmarksAdapter.notifyDataSetChanged()
        binding.progressBar.visibility=View.GONE
    }

    private fun onArticleClicked(article: Article) {
        val intent = Intent(context, NewsDetailActivity::class.java).apply {
            putExtra("ARTICLE", article)
        }
        startActivity(intent)
    }
}
