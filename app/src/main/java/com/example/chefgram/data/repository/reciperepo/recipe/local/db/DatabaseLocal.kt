package com.example.chefgram.data.repository.reciperepo.recipe.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.ingredientcache.IngredientCacheDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.ingredientcache.IngredientCacheEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipecache.RecipeCacheDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipecache.RecipeCacheEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.ingredient.IngredientDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.ingredient.IngredientEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipe.RecipeDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipe.RecipeEntity

@Database(
    entities = [RecipeEntity::class, IngredientEntity::class, IngredientCacheEntity::class, RecipeCacheEntity::class],
    version = 2,
    exportSchema = false
)
abstract class DatabaseLocal : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeCacheDAO(): RecipeCacheDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun ingredientCacheDao(): IngredientCacheDao
}