package com.example.chefgram.data.repository.local.db.filteringredient

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FilterIngredientDao {

    @Query("SELECT * FROM filter_ingredient")
    fun getFilterIngredients(): List<FilterIngredient>

    @Query("SELECT * FROM filter_ingredient WHERE id = :id")
    fun getFilterIngredientById(id: Int): FilterIngredient
}