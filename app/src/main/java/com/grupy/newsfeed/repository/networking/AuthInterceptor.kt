package com.grupy.newsfeed.repository.networking

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().build())
            .addHeader("Content-Type","application/json")

        return chain.proceed(request.build())
    }
}