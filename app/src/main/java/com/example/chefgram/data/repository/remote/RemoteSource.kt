package com.example.chefgram.data.repository.remote

import com.example.chefgram.data.repository.remote.recipemodel.RecipeDto

interface RemoteSource {

    suspend fun getRecipes(): List<RecipeDto>

}