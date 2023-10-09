package com.example.chefgram.data.repository

import com.example.chefgram.domain.model.Meal

interface MealsRepository {
    suspend fun fetchMeals(): List<Meal>
}
