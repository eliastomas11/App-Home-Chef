package com.example.chefgram.data.repository.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao  {

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun saveToFavorites(recipe: FavoriteRecipeEntity): Long
    @Query("SELECT * FROM MealEntity")
    fun getFavorites(): List<FavoriteRecipeEntity>
    @Delete
    fun deleteFromFavorites(recipe: FavoriteRecipeEntity)

}
