package com.example.chefgram.data.repository.local.db.cache.recipecache

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.chefgram.data.repository.local.db.ingredient.IngredientEntity

@Entity(tableName = "recipe_cache")
data class RecipeCacheEntity(
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