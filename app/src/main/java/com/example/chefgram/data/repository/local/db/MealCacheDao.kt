package com.example.chefgram.data.repository.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MealCacheDao {

    @Query("SELECT * FROM MealEntity")
    fun getMeals(): List<MealEntity>
    @Insert
    fun saveMeals(mealsEntity: List<MealEntity>)
    @Query("DELETE FROM MealEntity")
    fun clearCache()
    @Query("SELECT * FROM MealEntity WHERE id = :id")
    fun getMealById(id: Int): MealEntity

}
