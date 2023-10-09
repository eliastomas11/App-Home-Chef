package com.example.chefgram.data.repository.local.db

import androidx.room.Dao
import com.example.chefgram.data.repository.local.MealEntity

@Dao
interface MealDao  {
    fun saveToFavorites(meal: MealEntity): Boolean
    fun getFavorites(): List<MealEntity>
    fun deleteFromFavorites(meal: MealEntity): Boolean

}
