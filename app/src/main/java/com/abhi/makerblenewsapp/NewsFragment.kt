package com.abhi.makerblenewsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi.makerblenewsapp.databinding.FragmentNewsBinding
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import java.lang.Exception

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val apiKey = "b9ab6ee625984404aab774fa1fb2eabd"
    private lateinit var newsAdapter: NewsAdapter
    private val articlesList = mutableListOf<Article>() // Hold the list of articles

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility=View.VISIBLE
        setupRecyclerView() // Setup RecyclerView
        fetchNews() // Fetch news

    }

    // Setup RecyclerView and Adapter
    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(articlesList) { article -> onArticleClicked(article) }
        binding.rcv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    // Fetch News Data from API
    private fun fetchNews() {
        val url = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=$apiKey"

        val stringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener<String> { response ->
                Log.d("NewsFragment", "Response: $response")

                try {
                    val newsApiResponse = Gson().fromJson(response, NewsApiResponse::class.java)
                    articlesList.clear() // Clear the list before adding new data
                    articlesList.addAll(newsApiResponse.articles)
                    newsAdapter.notifyDataSetChanged() // Notify adapter of data change
                } catch (e: Exception) {
                    Toast.makeText(context, "Error parsing data", Toast.LENGTH_SHORT).show()
                    Log.e("NewsFragment", "Parsing error: ${e.message}")
                }
                binding.emptybg.visibility=View.GONE
                binding.progressBar.visibility=View.GONE
            },
            Response.ErrorListener { error ->
                val errorMessage = error.networkResponse?.let {
                    val errorBody = String(it.data)
                    "Error: $errorBody"
                } ?: "Network Error: ${error.message}"
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("NewsFragment", errorMessage)
                binding.progressBar.visibility=View.GONE
                binding.emptybg.visibility=View.VISIBLE
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
                return headers
            }
        }

        VolleyInstance.getInstance(requireContext()).addToRequestQueue(stringRequest)
    }

    // Handle Article Click
    private fun onArticleClicked(article: Article) {
        val intent = Intent(context, NewsDetailActivity::class.java).apply {
            putExtra("ARTICLE", article)
        }
        startActivity(intent)
    }
}
