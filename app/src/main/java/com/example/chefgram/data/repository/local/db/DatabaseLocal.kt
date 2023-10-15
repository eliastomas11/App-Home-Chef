package com.example.chefgram.data.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chefgram.data.repository.local.db.filteringredient.FilterIngredient
import com.example.chefgram.data.repository.local.db.filteringredient.FilterIngredientDao
import com.example.chefgram.data.repository.local.db.ingredient.IngredientEntity
import com.example.chefgram.data.repository.local.db.cache.recipecache.RecipeCacheDAO
import com.example.chefgram.data.repository.local.db.recipe.RecipeDao
import com.example.chefgram.data.repository.local.db.recipe.RecipeEntity
import com.example.chefgram.data.repository.local.db.recipewithingredient.RecipeIngredientCrossRef
import com.example.chefgram.data.repository.local.db.recipewithingredient.RecipeWithIngredientDao

@Database(entities = [RecipeEntity::class, IngredientEntity::class, RecipeIngredientCrossRef::class,FilterIngredient::class], version = 2, exportSchema = false)
abstract class DatabaseLocal : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    abstract fun recipeCacheDAO(): RecipeCacheDAO

    abstract fun recipeWithIngredientDao(): RecipeWithIngredientDao

    abstract fun filterIngredientDao(): FilterIngredientDao
}