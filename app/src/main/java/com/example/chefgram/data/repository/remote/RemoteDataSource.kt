package com.example.chefgram.data.repository.remote

import com.example.chefgram.common.toMealDto
import com.example.chefgram.data.mealremotemodel.MealDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val mealService: MealServiceApi) : RemoteSource {

    override suspend fun getMeals(): List<MealDto> {
        return mealService.getMeals().toMealDto()
    }

}
