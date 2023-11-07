package com.example.chefgram.data.repository.reciperepo.recipe.local.db.ingredient

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IngredientDao {

    @Query("SELECT * FROM ingredient")
    fun getAll(): List<IngredientEntity>

    @Query("SELECT * FROM ingredient WHERE id = :id")
    fun getById(id: Int): IngredientEntity?

    @Query("DELETE FROM ingredient")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveIngredients(vararg ingredientList: IngredientEntity)
}