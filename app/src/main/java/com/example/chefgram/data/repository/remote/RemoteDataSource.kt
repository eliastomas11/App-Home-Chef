package com.example.chefgram.data.repository.remote

import com.example.chefgram.common.toMealDto
import com.example.chefgram.data.mealremotemodel.MealDto
import com.example.chefgram.data.mealremotemodel.MealResponse

class RemoteDataSource {

    private val mealService = MealService.mealsService()

    suspend fun getMeals(): List<MealDto> {
        return mealService.getMeals().toMealDto()
    }

}
