package com.example.chefgram.data.repository.remote


import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MealService {

    private const val url = "https://tasty.p.rapidapi.com/"

    fun mealsService(): MealServiceApi {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MealServiceApi::class.java)
    }
}