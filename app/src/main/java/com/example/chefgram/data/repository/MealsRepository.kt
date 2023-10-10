package com.example.chefgram.data.repository

import com.example.chefgram.domain.model.Meal

interface MealsRepository {
    suspend fun fetchMeals(): List<Meal>
    suspend fun getMealById(id: Int): Meal
    suspend fun saveMeal(recipe: Meal?): Long
    suspend fun getFavorites(): List<Meal>
}
