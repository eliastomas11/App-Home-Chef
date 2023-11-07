package com.example.chefgram.data.repository.reciperepo.recipe.local.db.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipe.RecipeEntity

@Entity(tableName = "ingredient", primaryKeys = ["id", "recipe_id"], foreignKeys = [ForeignKey(entity = RecipeEntity::class, parentColumns = ["id"], childColumns = ["recipe_id"], onDelete = ForeignKey.CASCADE)])
data class IngredientEntity(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "original_name") val originalName: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "unit") val unit: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "image") val image:String,
    @ColumnInfo(name = "recipe_id") val recipeId: Int
)
