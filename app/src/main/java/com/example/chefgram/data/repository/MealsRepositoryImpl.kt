package com.example.chefgram.data.repository

import com.example.chefgram.common.toMeal
import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.data.mealremotemodel.RecipeDto
import com.example.chefgram.data.repository.local.LocalDataSource
import com.example.chefgram.data.repository.remote.RemoteDataSource
import com.example.chefgram.domain.model.Recipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MealsRepository {


    override suspend fun fetchMeals(): List<Recipe> {
        //throw RuntimeException("Not implemented")
        return withContext(dispatcher) {
            var mealsDto = localDataSource.getMeals()
            if (mealsDto.isEmpty()) {
                mealsDto = remoteDataSource.getMeals()
                localDataSource.saveMeals(mealsDto)
            }
            return@withContext mealsDto.map { it.toMeal() }
        }
    }

    override suspend fun getMealById(id: Int): Recipe {
        return withContext(dispatcher) {
            val meal = localDataSource.getMealById(id) ?: throw RuntimeException("Meal not found")
            return@withContext meal.toMeal()
        }
    }

    override suspend fun saveMeal(recipe: Recipe?): Long {
        return withContext(dispatcher) {
            return@withContext localDataSource.saveToFavorites(recipe!!.toRecipeDto())
        }
    }

    override suspend fun getFavoriteRecipes(): List<Recipe> {
        return withContext(dispatcher) {
            localDataSource.getFavorites().map { it.toMeal() }
        }
    }

    override suspend fun createRecipe(recipeDto: RecipeDto): Long {
        return withContext(dispatcher) {
            return@withContext localDataSource.saveToFavorites(recipeDto)
        }
    }

}
