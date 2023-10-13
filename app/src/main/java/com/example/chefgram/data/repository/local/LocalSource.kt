package com.example.chefgram.data.repository.local

import com.example.chefgram.data.mealremotemodel.RecipeDto

interface LocalSource {

    suspend fun getMeals(): List<RecipeDto>


    suspend fun saveMeals(mealsDto: List<RecipeDto>)


    suspend fun saveToFavorites(meal: RecipeDto): Long


    suspend fun getFavorites(): List<RecipeDto>

    suspend fun deleteFromFavorites(meal: RecipeDto)

    suspend fun clearCache()

    suspend fun getMealById(id: Int): RecipeDto?
}