package com.example.chefgram.data.repository

import com.example.chefgram.data.mealremotemodel.RecipeDto
import com.example.chefgram.domain.model.Recipe

interface MealsRepository {
    suspend fun fetchMeals(): List<Recipe>
    suspend fun getMealById(id: Int): Recipe
    suspend fun saveMeal(recipe: Recipe?): Long
    suspend fun getFavoriteRecipes(): List<Recipe>
    suspend fun createRecipe(recipeDto: RecipeDto): Long

}
