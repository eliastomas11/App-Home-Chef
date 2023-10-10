package com.example.chefgram.data.repository.local

import com.example.chefgram.common.toFavoriteRecipeEntity
import com.example.chefgram.common.toMealDto
import com.example.chefgram.common.toMealEntity
import com.example.chefgram.data.mealremotemodel.MealDto
import com.example.chefgram.data.repository.local.db.MealCacheDao
import com.example.chefgram.data.repository.local.db.MealDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mealDao: MealDao, private val mealCacheDao: MealCacheDao): LocalSource {

    override suspend fun getMeals(): List<MealDto> {
        return mealCacheDao.getMeals().map { it.toMealDto() }
    }

    override suspend fun saveMeals(mealsDto: List<MealDto>) {
        mealCacheDao.saveMeals(mealsDto.map { it.toMealEntity() })
    }

    override suspend fun saveToFavorites(meal: MealDto): Long {
        return mealDao.saveToFavorites(meal.toFavoriteRecipeEntity())
    }

    override suspend fun getFavorites(): List<MealDto> {
        return mealDao.getFavorites().map { it.toMealDto() }
    }

    override suspend fun deleteFromFavorites(meal: MealDto) {
         mealDao.deleteFromFavorites(meal.toFavoriteRecipeEntity())
    }

    override suspend fun clearCache() {
        mealCacheDao.clearCache()
    }

    override suspend fun getMealById(id: Int): MealDto? {
        return mealCacheDao.getMealById(id).toMealDto()
    }


}