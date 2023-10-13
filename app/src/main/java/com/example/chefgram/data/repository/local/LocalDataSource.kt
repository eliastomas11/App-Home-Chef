package com.example.chefgram.data.repository.local

import com.example.chefgram.common.toFavoriteRecipeEntity
import com.example.chefgram.common.toMealDto
import com.example.chefgram.common.toMealEntity
import com.example.chefgram.data.mealremotemodel.RecipeDto
import com.example.chefgram.data.repository.local.db.MealCacheDao
import com.example.chefgram.data.repository.local.db.MealDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mealDao: MealDao, private val mealCacheDao: MealCacheDao): LocalSource {

    override suspend fun getMeals(): List<RecipeDto> {
        return mealCacheDao.getMeals().map { it.toMealDto() }
    }

    override suspend fun saveMeals(mealsDto: List<RecipeDto>) {
        mealCacheDao.saveMeals(mealsDto.map { it.toMealEntity() })
    }

    override suspend fun saveToFavorites(meal: RecipeDto): Long {
        return mealDao.saveToFavorites(meal.toFavoriteRecipeEntity())
    }

    override suspend fun getFavorites(): List<RecipeDto> {
        return mealDao.getFavorites().map { it.toMealDto() }
    }

    override suspend fun deleteFromFavorites(meal: RecipeDto) {
         mealDao.deleteFromFavorites(meal.toFavoriteRecipeEntity())
    }

    override suspend fun clearCache() {
        mealCacheDao.clearCache()
    }

    override suspend fun getMealById(id: Int): RecipeDto? {
        return mealCacheDao.getMealById(id).toMealDto()
    }


}