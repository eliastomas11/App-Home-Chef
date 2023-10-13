package com.example.chefgram.data.repository.remote

import com.example.chefgram.data.mealremotemodel.RecipeDto

interface RemoteSource {

    suspend fun getMeals(): List<RecipeDto>

}