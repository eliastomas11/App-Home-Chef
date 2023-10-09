package com.example.chefgram.data.repository.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chefgram.data.repository.local.MealEntity


@Dao
interface MealCacheDao {

    @Query("SELECT * FROM MealEntity")
    fun getMeals(): List<MealEntity>
    @Insert
    fun saveMeals(mealsEntity: List<MealEntity>): Boolean
    @Query("DELETE FROM MealEntity")
    fun clearCache(): Boolean
    @Query("SELECT * FROM MealEntity WHERE id = :id")
    fun getMealById(id: Int): MealEntity

}
