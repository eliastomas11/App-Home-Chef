package com.example.chefgram.data.repository.local.db.recipewithingredient

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeWithIngredientDao {

    @Insert
    fun saveToRecipeWithIngredient(vararg recipeWithIngredient: RecipeIngredientCrossRef)

    @Delete
    fun deleteFromRecipeWithIngredient(recipeWithIngredient: RecipeIngredientCrossRef)

    @Query("SELECT * FROM recipe_ingredient")
    fun getRecipeWithIngredient(): List<RecipeIngredientCrossRef>

    @Query("SELECT favorite_recipe.*, ingredient.* FROM favorite_recipe JOIN recipe_ingredient ON favorite_recipe.id = recipe_ingredient.recipeId" +
            " JOIN ingredient ON recipe_ingredient.ingredientId = ingredientId")
    fun getRecipesWithIngredients(): List<RecipeWithIngredient>

    @Query("SELECT favorite_recipe.*, ingredient.* FROM favorite_recipe JOIN recipe_ingredient ON favorite_recipe.id = recipe_ingredient.recipeId" +
            " JOIN ingredient ON recipe_ingredient.ingredientId = ingredientId" +
            " WHERE recipeId = :recipeId")
    fun getRecipesWithIngredientsById(recipeId: Int): RecipeWithIngredient
}