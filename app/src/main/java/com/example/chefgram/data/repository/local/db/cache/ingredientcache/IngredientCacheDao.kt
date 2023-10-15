package com.example.chefgram.data.repository.local.db.cache.ingredientcache

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface IngredientCacheDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveToIngredientCache(ingredientCacheEntity: List<IngredientCacheEntity>)

    @Query("DELETE FROM ingredient_cache")
    fun clearCache()

    @Query("SELECT * FROM ingredient_cache")
    fun getIngredient(): List<IngredientCacheEntity>


}