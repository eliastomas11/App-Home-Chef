package com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipecache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipewithingredientcache.RecipeWithIngredientCache


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
    @Transaction
    @Query("SELECT recipe_cache.* FROM recipe_cache " +
            "JOIN ingredient_cache ON recipe_cache.id = ingredient_cache.recipe_cache_id " +
            "WHERE recipe_cache.name LIKE '%' || :query || '%' " +
            "OR recipe_cache.created_by LIKE '%' || :query || '%' " +
            "OR ingredient_cache.name LIKE '%' || :query || '%'")
    fun filterRecipes(query: String): List<RecipeWithIngredientCache>?

}
