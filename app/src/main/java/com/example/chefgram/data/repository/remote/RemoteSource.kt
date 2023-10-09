package com.example.chefgram.data.repository.remote

import com.example.chefgram.common.toMealDto
import com.example.chefgram.data.mealremotemodel.MealDto

interface RemoteSource {

    suspend fun getMeals(): List<MealDto>

}