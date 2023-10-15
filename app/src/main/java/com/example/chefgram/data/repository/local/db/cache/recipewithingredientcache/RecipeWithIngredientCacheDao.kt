package com.example.chefgram.data.repository.local.db.cache.recipewithingredientcache

import androidx.room.Insert
import androidx.room.Query

interface RecipeWithIngredientCacheDao {

    @Insert
    fun saveToRecipeWithIngredient(vararg recipeWithIngredient: RecipeWithIngredientCacheCrossRef)

    @Query("SELECT * FROM recipe_ingredient")
    fun getRecipeWithIngredient(): List<RecipeWithIngredientCacheCrossRef>

}