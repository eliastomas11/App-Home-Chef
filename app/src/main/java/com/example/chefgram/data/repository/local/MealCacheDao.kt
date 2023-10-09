package com.example.chefgram.data.repository.local


interface MealCacheDao {

    fun getMeals(): List<MealEntity>
    fun saveMeals(mealsDto: List<MealEntity>): Boolean
    fun clearCache(): Boolean

}
