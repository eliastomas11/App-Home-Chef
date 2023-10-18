package com.example.chefgram.data.repository

import com.example.chefgram.data.repository.remote.recipemodel.RecipeDto
import com.example.chefgram.domain.model.Category
import com.example.chefgram.domain.model.Recipe

interface RecipeRepository {
    suspend fun fetchRecipes(): List<Recipe>
    suspend fun getRecipesById(id: Int): Recipe
    suspend fun saveRecipes(recipe: Recipe): Long
    suspend fun getFavoriteRecipes(): List<Recipe>
    suspend fun createRecipe(recipeDto: RecipeDto): Long
    suspend fun filterRecipes(query: String): List<Recipe>?

}
