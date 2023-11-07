package com.example.chefgram.data.repository.reciperepo.recipe.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.chefgram.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class RecipeInterceptor @Inject constructor(val context: Context): Interceptor {

    private val key = BuildConfig.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val headersChain = chain.request()
            .newBuilder()
            .addHeader("x-api-key",key)
            .build()

        if(!isNetworkAvailable(context)) {
            //throw CustomException.NoNetworkConnection
        }
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

private fun isNetworkAvailable (context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}
