package com.example.chefgram.data.repository.local.db.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.chefgram.data.repository.local.db.recipewithingredient.RecipeWithIngredient

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveToFavorites(vararg favoriteRecipe: RecipeEntity): Long

    @Transaction
    @Query("SELECT * FROM recipe")
    fun getAllFavorites(): List<RecipeWithIngredient>

    @Delete
    fun deleteFromFavorites(recipe: RecipeEntity)

    @Transaction
    @Query("SELECT * FROM recipe WHERE id = :id")
    fun getFavoriteById(id: Int): RecipeWithIngredient
}
