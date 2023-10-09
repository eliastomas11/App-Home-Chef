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

    companion object {
        private const val DATABASE_NAME = "meals.db"

        @Volatile
        private var INSTANCE: DatabaseLocal? = null

        fun getInstance(context: Context): DatabaseLocal {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DatabaseLocal::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE!!
            }
        }
    }

}