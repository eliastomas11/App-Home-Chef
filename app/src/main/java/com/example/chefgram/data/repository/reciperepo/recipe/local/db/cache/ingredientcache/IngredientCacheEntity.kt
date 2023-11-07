package com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.ingredientcache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipecache.RecipeCacheEntity

@Entity(tableName = "ingredient_cache", primaryKeys = ["id","recipe_cache_id"], foreignKeys = [ForeignKey(entity = RecipeCacheEntity::class, parentColumns = ["id"], childColumns = ["recipe_cache_id"], onDelete = ForeignKey.CASCADE)])
data class IngredientCacheEntity(
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "original_name") val originalName: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "unit") val unit: String,
    @ColumnInfo (name = "type") val type: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "recipe_cache_id") val recipeCacheId: Int
)
