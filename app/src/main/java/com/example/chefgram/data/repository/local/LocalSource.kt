package com.example.chefgram.data.repository.local

import com.example.chefgram.data.mealremotemodel.MealDto

interface LocalSource {

    suspend fun getMeals(): List<MealDto>


    suspend fun saveMeals(mealsDto: List<MealDto>): Boolean


    suspend fun saveToFavorites(meal: MealDto): Boolean


    suspend fun getFavorites(): List<MealDto>

    suspend fun deleteFromFavorites(meal: MealDto): Boolean

    suspend fun clearCache(): Boolean

    suspend fun getMealById(id: Int): MealDto?
}