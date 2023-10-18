package com.example.chefgram.data.repository.local

import com.example.chefgram.data.repository.remote.recipemodel.CategoryDto
import com.example.chefgram.data.repository.remote.recipemodel.RecipeDto
import com.example.chefgram.domain.model.Category
import com.example.chefgram.domain.model.IngredientFilterItem

interface LocalSource {

    suspend fun getRecipes(): List<RecipeDto>

    suspend fun saveRecipes(recipesDto: List<RecipeDto>)


    suspend fun saveToFavorites(recipe: RecipeDto): Long


    suspend fun getFavorites(): List<RecipeDto>

    suspend fun deleteFromFavorites(recipe: RecipeDto)

    suspend fun clearCache()

    suspend fun getRecipeById(id: Int): RecipeDto?

    suspend fun getFavoriteRecipeById(id: Int): RecipeDto?

    suspend fun filterRecipes(query: String): List<RecipeDto>?
}