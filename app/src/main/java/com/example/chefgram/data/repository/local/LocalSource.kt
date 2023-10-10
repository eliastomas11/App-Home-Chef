package com.example.chefgram.data.repository.local

import com.example.chefgram.data.mealremotemodel.MealDto

interface LocalSource {

    suspend fun getMeals(): List<MealDto>


    suspend fun saveMeals(mealsDto: List<MealDto>)


    suspend fun saveToFavorites(meal: MealDto): Long


    suspend fun getFavorites(): List<MealDto>

    suspend fun deleteFromFavorites(meal: MealDto)

    suspend fun clearCache()

    suspend fun getMealById(id: Int): MealDto?
}