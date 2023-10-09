package com.example.chefgram.data.repository.local

import com.example.chefgram.common.toMealDto
import com.example.chefgram.common.toMealEntity
import com.example.chefgram.data.mealremotemodel.MealDto
import com.example.chefgram.data.mealremotemodel.MealResponse

class LocalDataSource(private val mealDao: MealDao,private val mealCacheDao: MealCacheDao) {

    fun getMeals(): List<MealDto> {
        return mealCacheDao.getMeals().map { it.toMealDto() }
    }

    fun saveMeals(mealsDto: List<MealDto>): Boolean {
        return mealCacheDao.saveMeals(mealsDto.map { it.toMealEntity() })
    }

    fun saveToFavorites(meal: MealDto): Boolean {
        return mealDao.saveToFavorites(meal.toMealEntity())
    }

    fun getFavorites(): List<MealDto> {
        return mealDao.getFavorites().map { it.toMealDto() }
    }

    fun deleteFromFavorites(meal: MealDto): Boolean {
        return mealDao.deleteFromFavorites(meal.toMealEntity())
    }

    fun clearCache(): Boolean {
        return mealCacheDao.clearCache()
    }


}