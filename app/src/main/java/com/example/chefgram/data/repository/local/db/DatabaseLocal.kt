package com.example.chefgram.data.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chefgram.data.repository.local.db.cache.ingredientcache.IngredientCacheDao
import com.example.chefgram.data.repository.local.db.cache.ingredientcache.IngredientCacheEntity
import com.example.chefgram.data.repository.local.db.cache.recipecache.RecipeCacheDao
import com.example.chefgram.data.repository.local.db.cache.recipecache.RecipeCacheEntity
import com.example.chefgram.data.repository.local.db.categories.CategoryDao
import com.example.chefgram.data.repository.local.db.categories.CategoryEntity
import com.example.chefgram.data.repository.local.db.filteringredient.FilterIngredient
import com.example.chefgram.data.repository.local.db.filteringredient.FilterIngredientDao
import com.example.chefgram.data.repository.local.db.ingredient.IngredientDao
import com.example.chefgram.data.repository.local.db.ingredient.IngredientEntity
import com.example.chefgram.data.repository.local.db.recipe.RecipeDao
import com.example.chefgram.data.repository.local.db.recipe.RecipeEntity

@Database(
    entities = [RecipeEntity::class, IngredientEntity::class, FilterIngredient::class, IngredientCacheEntity::class, RecipeCacheEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = true
)
abstract class DatabaseLocal : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeCacheDAO(): RecipeCacheDao
    abstract fun filterIngredientDao(): FilterIngredientDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun ingredientCacheDao(): IngredientCacheDao
    abstract fun categoryDao(): CategoryDao
}