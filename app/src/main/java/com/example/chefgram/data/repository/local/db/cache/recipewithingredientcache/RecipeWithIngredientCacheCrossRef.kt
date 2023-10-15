package com.example.chefgram.data.repository.local.db.cache.recipewithingredientcache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.chefgram.data.repository.local.db.cache.ingredientcache.IngredientCacheEntity
import com.example.chefgram.data.repository.local.db.cache.recipecache.RecipeCacheEntity
import com.example.chefgram.data.repository.local.db.ingredient.IngredientEntity
import com.example.chefgram.data.repository.local.db.recipe.RecipeEntity

@Entity(
    tableName = "recipe_ingredient_cache",
    primaryKeys = ["recipe_cache_id", "ingredient_cache_id"],
    foreignKeys = [
        ForeignKey(
            entity = RecipeCacheEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipe_cache_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IngredientCacheEntity::class,
            parentColumns = ["id"],
            childColumns = ["ingredient_cache_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class RecipeWithIngredientCacheCrossRef(
    @ColumnInfo(name = "recipe_cache_id")
    val recipeCacheId: Int,
    @ColumnInfo(name = "ingredient_cache_id")
    val ingredientCacheId: Int)
