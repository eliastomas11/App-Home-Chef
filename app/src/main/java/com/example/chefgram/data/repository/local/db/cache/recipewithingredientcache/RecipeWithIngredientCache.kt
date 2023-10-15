package com.example.chefgram.data.repository.local.db.cache.recipewithingredientcache

import androidx.room.Embedded
import androidx.room.Relation
import com.example.chefgram.data.repository.local.db.cache.ingredientcache.IngredientCacheEntity
import com.example.chefgram.data.repository.local.db.cache.recipecache.RecipeCacheEntity

data class RecipeWithIngredientCache(
    @Embedded val recipe: RecipeCacheEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipe_cache_id",
    )
    val ingredientList: List<IngredientCacheEntity>
)
