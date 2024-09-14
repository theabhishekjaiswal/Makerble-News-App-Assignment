package com.abhi.makerblenewsapp

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BookmarkManager {
    private const val PREFS_NAME = "bookmarks_prefs"
    private const val BOOKMARKS_KEY = "bookmarks"

    fun addBookmark(context: Context, article: Article) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val json = gson.toJson(article)
        val bookmarks = getBookmarks(context).toMutableList()
        bookmarks.add(article) // Store the Article object directly
        editor.putString(BOOKMARKS_KEY, gson.toJson(bookmarks))
        editor.apply()
    }

    fun removeBookmark(context: Context, article: Article) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val bookmarks = getBookmarks(context).toMutableList()
        bookmarks.remove(article) // Remove the Article object directly
        editor.putString(BOOKMARKS_KEY, gson.toJson(bookmarks))
        editor.apply()
    }

    fun getBookmarks(context: Context): List<Article> {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(BOOKMARKS_KEY, "[]")
        val type = object : TypeToken<List<Article>>() {}.type
        return gson.fromJson(json, type)
    }
}
