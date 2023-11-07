package com.example.chefgram.data.repository.reciperepo.recipe

import android.util.Log
import com.example.chefgram.common.errorhandling.CustomException
import com.example.chefgram.common.toRecipe
import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.data.repository.reciperepo.recipe.local.LocalSource
import com.example.chefgram.data.repository.reciperepo.recipe.remote.RemoteSource
import com.example.chefgram.data.repository.reciperepo.recipe.remote.recipemodel.RecipeDto
import com.example.chefgram.domain.model.Recipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val localDataSource: LocalSource,
    private val remoteDataSource: RemoteSource
) : RecipeRepository {


    override suspend fun fetchRecipes(): List<Recipe> {
        return try {
            withContext(dispatcher) {
                var recipeDto = localDataSource.getRecipes()
                if (recipeDto.isEmpty()) {
                    Log.i("ENTRO POR NETWORK", "ENTRO POR NETWORK")
                    recipeDto = remoteDataSource.getRecipes()
                    localDataSource.saveRecipes(recipeDto)
                }
                return@withContext recipeDto.map { it.toRecipe() }
            }
        } catch (e: SocketTimeoutException) {
            throw CustomException.TimeOutException
        }
    }


    override suspend fun getRecipesById(id: Int): Recipe {
        return withContext(dispatcher) {
            val recipe = localDataSource.getRecipeById(id)
            return@withContext recipe.toRecipe()
        }
    }

    override suspend fun saveRecipes(recipe: Recipe): Long {
        return withContext(dispatcher) {
            return@withContext localDataSource.saveToFavorites(recipe.toRecipeDto())
        }
    }

    override suspend fun getFavoriteRecipes(): List<Recipe> {
        return withContext(dispatcher) {
            return@withContext localDataSource.getFavorites().map { it.toRecipe() }
        }
    }

    override suspend fun createRecipe(recipeDto: RecipeDto): Long {
        return withContext(dispatcher) {
            return@withContext localDataSource.saveToFavorites(recipeDto)
        }
    }

    override suspend fun filterRecipes(query: String): Set<Recipe> {
        return withContext(dispatcher) {
            return@withContext localDataSource.filterRecipes(query).map { it.toRecipe() }.toSet()
        }
    }

    override suspend fun clearCache() {
        withContext(dispatcher) {
            localDataSource.clearCache()
        }
    }

    override suspend fun loadMoreRecipes() {
        withContext(dispatcher) {
            val recipeList = remoteDataSource.getRecipes()
            localDataSource.saveRecipes(recipeList)
        }
    }


}
