package com.example.chefgram.data.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MealEntity::class, FavoriteRecipeEntity::class], version = 1, exportSchema = false)
abstract class DatabaseLocal : RoomDatabase() {
    abstract fun mealDao(): MealDao

    abstract fun mealCacheDao(): MealCacheDao


}