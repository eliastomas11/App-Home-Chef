package com.example.chefgram.data.repository.remote

import com.example.chefgram.common.toMealDto
import com.example.chefgram.data.mealremotemodel.RecipeDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val mealService: MealServiceApi) : RemoteSource {



    override suspend fun getMeals(): List<RecipeDto> {
        return mealService.getMeals().toMealDto()
    }

}
