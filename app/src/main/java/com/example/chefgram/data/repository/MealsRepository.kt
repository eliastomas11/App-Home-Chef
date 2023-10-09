package com.example.chefgram.data.repository

import com.example.chefgram.ui.model.Meal

interface MealsRepository {
    suspend fun fetchMeals(): List<Meal>
}
