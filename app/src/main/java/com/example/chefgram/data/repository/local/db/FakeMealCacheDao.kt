package com.example.chefgram.data.repository.local.db

import com.example.chefgram.data.repository.local.MealEntity

class FakeMealCacheDao: MealCacheDao {
    override fun getMeals(): List<MealEntity> {
        return emptyList()
    }

    override fun saveMeals(mealsDto: List<MealEntity>): Boolean {
        return true
    }

    override fun clearCache(): Boolean {
        return true
    }

    override fun getMealById(id: Int): MealEntity {
        TODO()
    }
}