package com.example.chefgram.data.repository

import android.util.Log
import com.example.chefgram.common.toRecipe
import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.data.repository.local.LocalSource
import com.example.chefgram.data.repository.remote.RemoteSource
import com.example.chefgram.data.repository.remote.recipemodel.RecipeDto
import com.example.chefgram.domain.model.Recipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val localDataSource: LocalSource,
    private val remoteDataSource: RemoteSource
) : RecipeRepository {


    override suspend fun fetchRecipes(): List<Recipe> {
        return withContext(dispatcher) {
            var recipeDto = localDataSource.getRecipes()
            if (recipeDto.isEmpty()) {
                recipeDto = remoteDataSource.getRecipes()
                localDataSource.saveRecipes(recipeDto)
            }
            return@withContext recipeDto.map { it.toRecipe() }
        }
    }


    override suspend fun getRecipesById(id: Int): Recipe {
        return withContext(dispatcher) {
            val recipe = localDataSource.getRecipeById(id)
            return@withContext recipe!!.toRecipe()
        }
    }

    override suspend fun saveRecipes(recipe: Recipe): Long {
        return withContext(dispatcher) {
            return@withContext localDataSource.saveToFavorites(recipe.toRecipeDto())
        }
    }

    override suspend fun getFavoriteRecipes(): List<Recipe> {
        return withContext(dispatcher) {
            localDataSource.getFavorites().map { it.toRecipe() }
        }
    }

    override suspend fun createRecipe(recipeDto: RecipeDto): Long {
        return withContext(dispatcher) {
            return@withContext localDataSource.saveToFavorites(recipeDto)
        }
    }

}
