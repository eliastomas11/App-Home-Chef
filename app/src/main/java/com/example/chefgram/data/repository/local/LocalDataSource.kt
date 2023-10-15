package com.example.chefgram.data.repository.local

import com.example.chefgram.common.toIngredientCache
import com.example.chefgram.common.toIngredientEntity
import com.example.chefgram.common.toRecipeCache
import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.common.toRecipeEntity
import com.example.chefgram.data.repository.local.db.cache.ingredientcache.IngredientCacheDao
import com.example.chefgram.data.repository.local.db.cache.recipecache.RecipeCacheDao
import com.example.chefgram.data.repository.local.db.ingredient.IngredientDao
import com.example.chefgram.data.repository.local.db.recipe.RecipeDao
import com.example.chefgram.data.repository.remote.recipemodel.RecipeDto
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao,
    private val recipeCacheDao: RecipeCacheDao,
    private val ingredientCacheDao: IngredientCacheDao,
    private val ingredientDao: IngredientDao,
) : LocalSource {

    override suspend fun getRecipes(): List<RecipeDto> {
        return recipeCacheDao.getRecipes().map { it.toRecipeDto() }
    }

    override suspend fun saveRecipes(recipeDto: List<RecipeDto>) {
        recipeCacheDao.saveRecipe(recipeDto.map { it.toRecipeCache() })
        ingredientCacheDao.saveToIngredientCache(recipeDto.flatMap { recipe ->
            recipe.ingredient.map {
                it.toIngredientCache().copy(recipeCacheId = recipe.id)
            }
        })
    }

    override suspend fun saveToFavorites(recipe: RecipeDto): Long {
        recipeDao.saveToFavorites(recipe.toRecipeEntity())
        recipe.ingredient.forEach {
            ingredientDao.saveIngredients(it.toIngredientEntity().copy(id = recipe.id))
        }
        return 5
    }

    override suspend fun getFavorites(): List<RecipeDto> {
        return recipeDao.getAllFavorites().map { it.toRecipeDto() }
    }

    override suspend fun deleteFromFavorites(recipe: RecipeDto) {
        recipeDao.deleteFromFavorites(recipe.toRecipeEntity())
    }

    override suspend fun clearCache() {
        recipeCacheDao.clearCache()
    }

    override suspend fun getRecipeById(id: Int): RecipeDto {
        return recipeCacheDao.getRecipeById(id)?.toRecipeDto() ?: throw RuntimeException("Recipe not found")
    }

    override suspend fun getFavoriteRecipeById(id: Int): RecipeDto {
        return recipeDao.getFavoriteById(id)?.toRecipeDto() ?: throw RuntimeException("Recipe not found")
    }
}