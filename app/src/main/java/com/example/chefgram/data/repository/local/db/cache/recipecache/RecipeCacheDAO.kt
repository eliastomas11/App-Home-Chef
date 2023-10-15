package com.example.chefgram.data.repository.local.db.cache.recipecache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.chefgram.data.repository.local.db.cache.recipewithingredientcache.RecipeWithIngredientCache


@Dao
interface RecipeCacheDAO {

    @Transaction
    @Query("SELECT * FROM recipe")
    fun getRecipes(): List<RecipeWithIngredientCache>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveRecipe(recipes: List<RecipeCacheEntity>)

    @Query("DELETE FROM recipe")
    fun clearCache()

    @Transaction
    @Query("SELECT * FROM recipe WHERE id = :id")
    fun getRecipeById(id: Int): RecipeWithIngredientCache

}
