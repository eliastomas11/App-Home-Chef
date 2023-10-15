package com.example.chefgram.data.repository.remote

import com.example.chefgram.common.toRecipeDto
import com.example.chefgram.data.repository.remote.recipemodel.RecipeDto
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val mealService: RecipeServiceApi) :
    RemoteSource {

    override suspend fun getRecipes(): List<RecipeDto> {
        //Por que tira null?
        return mealService.getRecipes().body()?.recipeResponseDtos?.map { it.toRecipeDto() }
            ?: emptyList()
    }

}
