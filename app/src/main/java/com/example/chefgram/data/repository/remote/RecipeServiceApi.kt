package com.example.chefgram.data.repository.remote

import com.example.chefgram.data.repository.remote.recipemodel.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeServiceApi {

    @GET("recipes/random")
    suspend fun getRecipes(@Query("number") number: Int = 50): Response<RecipeResponse>
}
