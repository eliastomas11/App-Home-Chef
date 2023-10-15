package com.example.chefgram.data.repository.local.db.cache.ingredientcache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_cache")
data class IngredientCacheEntity(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "original_name") val originalName: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "unit") val unit: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "recipe_cache_id") val recipeCacheId: Int
)
