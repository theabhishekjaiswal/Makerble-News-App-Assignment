package com.abhi.makerblenewsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhi.makerblenewsapp.databinding.ItemNewsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class NewsAdapter(
    private val articles: List<Article>,
    private val onItemClick: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.tvSource.text = article.source.name
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            Glide.with(binding.root.context)
                .load(article.urlToImage)
                .apply(RequestOptions().placeholder(R.drawable.baseline_image_search_24).error(R.drawable.baseline_image_search_24))
                .into(binding.ivImage)

            val isBookmarked = BookmarkManager.getBookmarks(binding.root.context).contains(article)
            binding.btnBookmark.setImageResource(
                if (isBookmarked) R.drawable.baseline_bookmarked else R.drawable.baseline_bookmark_border_24
            )

            binding.btnBookmark.setOnClickListener {
                if (isBookmarked) {
                    BookmarkManager.removeBookmark(binding.root.context, article)
                    binding.btnBookmark.setImageResource(R.drawable.baseline_bookmark_border_24)
                } else {
                    BookmarkManager.addBookmark(binding.root.context, article)
                    binding.btnBookmark.setImageResource(R.drawable.baseline_bookmarked)
                }
            }

            binding.root.setOnClickListener {
                onItemClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size
}
