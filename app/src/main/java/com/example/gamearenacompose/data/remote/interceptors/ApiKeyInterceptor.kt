package com.example.gamearenacompose.data.remote.interceptors

import com.example.gamearenacompose.utils.RAWG_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val interceptedUrl = original.url.newBuilder().addQueryParameter("key", RAWG_API_KEY).build()
        original = original.newBuilder().url(interceptedUrl).build()
        return chain.proceed(original)
    }
}