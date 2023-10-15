package com.example.chefgram.data.repository.local.db.recipewithingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.chefgram.data.repository.local.db.ingredient.IngredientEntity
import com.example.chefgram.data.repository.local.db.recipe.RecipeEntity

@Entity(
    tableName = "recipe_ingredient", primaryKeys = ["recipe_id", "ingredient_id"], foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IngredientEntity::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class RecipeIngredientCrossRef(
    @ColumnInfo(name = "recipe_id") val recipeId: Int,
    @ColumnInfo(name = "ingredient_id") val ingredientId: Int
)