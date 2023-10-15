package com.example.chefgram.data.repository.remote

import com.example.chefgram.data.repository.remote.recipemodel.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeServiceApi {

    @GET("recipes/random?apiKey=74163ae884ba47179882de8e5467c1aa")
    suspend fun getRecipes(@Query("number") number: Int = 30): Response<RecipeResponse>
}
