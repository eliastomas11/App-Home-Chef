package com.example.chefgram.data.repository.reciperepo.recipe.local

import com.example.chefgram.common.errorhandling.CustomException
import com.example.chefgram.common.toIngredientCache
import com.example.chefgram.common.toIngredientEntity
import com.example.chefgram.common.toRecipeCache
import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.common.toRecipeEntity
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.ingredientcache.IngredientCacheDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.cache.recipecache.RecipeCacheDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.ingredient.IngredientDao
import com.example.chefgram.data.repository.reciperepo.recipe.local.db.recipe.RecipeDao
import com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel.RecipeDto
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

    override suspend fun saveRecipes(recipesDto: List<RecipeDto>) {
        recipeCacheDao.saveRecipe(recipesDto.map { it.toRecipeCache() })
        ingredientCacheDao.saveToIngredientCache(recipesDto.flatMap { recipe ->
            recipe.ingredient.map {
                it.toIngredientCache().copy(recipeCacheId = recipe.id)
            }
        })
    }

    override suspend fun saveToFavorites(recipe: RecipeDto): Long {
        val saved = recipeDao.saveToFavorites(recipe.toRecipeEntity())
        recipe.ingredient.forEach {
            ingredientDao.saveIngredients(it.toIngredientEntity().copy(recipeId = recipe.id))
        }
        return saved
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
        return recipeCacheDao.getRecipeById(id)?.toRecipeDto() ?: throw CustomException.RecipeNotFoundException
    }

    override suspend fun getFavoriteRecipeById(id: Int): RecipeDto {
        return recipeDao.getFavoriteById(id)?.toRecipeDto() ?: throw CustomException.RecipeNotFoundException
    }

    override suspend fun filterRecipes(query: String): List<RecipeDto> {
        return recipeCacheDao.filterRecipes(query)?.map { it.toRecipeDto() } ?: emptyList()
    }

}