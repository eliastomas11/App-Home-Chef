package com.example.chefgram.data.repository.local.db.cache.recipecache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.chefgram.data.repository.local.db.cache.recipewithingredientcache.RecipeWithIngredientCache


@Dao
interface RecipeCacheDao {

    @Transaction
    @Query("SELECT * FROM recipe_cache")
    fun getRecipes(): List<RecipeWithIngredientCache>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveRecipe(recipes: List<RecipeCacheEntity>)

    @Query("DELETE FROM recipe_cache")
    fun clearCache()

    @Transaction
    @Query("SELECT * FROM recipe_cache WHERE id = :id")
    fun getRecipeById(id: Int): RecipeWithIngredientCache?
    @Query("SELECT * FROM recipe_cache WHERE name LIKE '%' || :query || '%'")
    fun filterRecipes(query: String): List<RecipeWithIngredientCache>?

}
