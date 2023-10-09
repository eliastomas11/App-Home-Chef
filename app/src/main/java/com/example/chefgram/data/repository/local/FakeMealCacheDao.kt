package com.example.chefgram.data.repository.local

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
}