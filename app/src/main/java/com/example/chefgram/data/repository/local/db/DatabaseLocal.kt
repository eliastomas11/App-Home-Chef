package com.example.chefgram.data.repository.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chefgram.data.repository.local.MealEntity

@Database(entities = [MealEntity::class], version = 1, exportSchema = true)
abstract class DatabaseLocal : RoomDatabase() {
    abstract fun mealDao(): MealDao

    abstract fun mealCacheDao(): MealCacheDao


}