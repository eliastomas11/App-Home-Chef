package com.example.chefgram.data.repository.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealServiceApi {

    @GET("recipes/random")
    fun getMeals(@Query("number") number: Int = 30): Response<MealResponse>
}
