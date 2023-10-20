package com.example.chefgram.data.repository.remote

import com.example.chefgram.common.errorhandling.CustomException
import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.data.repository.remote.recipemodel.RecipeDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val recipeService: RecipeServiceApi) :
    RemoteSource {

    override suspend fun getRecipes(): List<RecipeDto>  {
        val response = recipeService.getRecipes()
        if(response.isSuccessful) {
            return response.body()!!.recipes.map { it.toRecipeDto() }
        } else {
            throw CustomException.ApiError(response.code())
        }

    }

}
