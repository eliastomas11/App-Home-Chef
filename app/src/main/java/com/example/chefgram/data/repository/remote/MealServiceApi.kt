package com.example.chefgram.data.repository.remote

import com.example.chefgram.data.mealremotemodel.MealResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface MealServiceApi {

    @GET("recipes/list?from=0&size=30")
    @Headers(
        "X-RapidAPI-Key: c3228c52eemsh971e37422a63b02p1fe0b5jsn9299e8084b2b",
        "X-RapidAPI-Host: tasty.p.rapidapi.com"
    )
    suspend fun getMeals(): MealResponse
}
