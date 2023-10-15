package com.example.chefgram.data.repository.local.db.ingredient

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient")
    fun getAll(): List<IngredientEntity>

    @Query("SELECT * FROM ingredient WHERE id = :id")
    fun getById(id: Int): IngredientEntity?

    @Query("DELETE FROM ingredient")
    fun clear()

    @Insert()
    fun saveIngredients(vararg ingredientList: IngredientEntity)
}