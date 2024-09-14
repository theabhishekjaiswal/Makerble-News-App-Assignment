package com.abhi.makerblenewsapp


import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyInstance private constructor(context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: VolleyInstance? = null

        fun getInstance(context: Context): VolleyInstance {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleyInstance(context.applicationContext).also { INSTANCE = it }
            }
        }
    }

    private val requestQueue: RequestQueue by lazy {
        // Use applicationContext to avoid leaking the activity or broadcast receiver if passed
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}
