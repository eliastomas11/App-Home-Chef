package com.example.chefgram.data.repository.remote

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class RecipeInterceptor: Interceptor {

    private val key = "74163ae884ba47179882de8e5467c1aa"

    override fun intercept(chain: Interceptor.Chain): Response {
        val headersChain = chain.request()
            .newBuilder()
            .addHeader("Content-Type:","application/json")
            .addHeader("x-api-key:",key)
            .build()

        val response =  chain.proceed(headersChain)

        if (response.body() != null) {
            val responseBodyString = response.body().toString()
            Log.d("HTTP Response", "Received response for ${headersChain.url()}: ${response.code()}, Body: $responseBodyString")

            val newResponseBody = ResponseBody.create(response.body()!!.contentType(), responseBodyString)
            response.newBuilder().body(newResponseBody).build()
        } else {
            Log.d("HTTP Response", "Received response for ${headersChain.url()}: ${response.code()}, Body is null")
        }
        return response
    }
}