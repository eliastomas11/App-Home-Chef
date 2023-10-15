package com.example.chefgram.data.repository.local.db.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "num_servings") val numServings: Int = 0,
    @ColumnInfo(name = "cook_time_minutes") val cookTimeMinutes: Int = 0,
    @ColumnInfo(name = "vegan_free") val veganFree: Boolean,
    @ColumnInfo(name = "dairy_free") val glutenFree: Boolean,
    @ColumnInfo(name = "gluten_free") val dairyFree: Boolean,
    @ColumnInfo(name = "very_healthy") val veryHealthy: Boolean,
    @ColumnInfo(name = "created_by") val createdBy: String,
)
