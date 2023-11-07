package com.example.chefgram.data.repository.reciperepo.recipe.remote

import com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel.RecipeDto

interface RemoteSource {

    suspend fun getRecipes(): List<RecipeDto>

}